package com.chehejia.demo.datasync.master;

import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.SyncInfo;
import android.content.SyncStatusObserver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chehejia.demo.datasync.master.mvp.Contract;
import com.chehejia.demo.datasync.master.mvp.PresenterImpl;
import com.chehejia.demo.datasync.master.mvp.ViewImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SyncAdapterMaster";

    Contract.View mViewImplementation = null;
    Contract.Presenter mPresenterImplementation = null;


    private static final String SLAVE1_AUTHORITY = "com.chehejia.demo.account.slave1";
    private static final String SLAVE2_AUTHORITY = "com.chehejia.demo.account.slave2";

    ContentObserver mSyncObserver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenterImplementation = new PresenterImpl();

        View root = findViewById(android.R.id.content);
        mViewImplementation = new ViewImpl();
        mViewImplementation.presenter(mPresenterImplementation);
        mViewImplementation.bind(root);

        mPresenterImplementation.view(mViewImplementation);
        mPresenterImplementation.accounts(AccountManager.get(this));


        mSyncObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                Log.d(TAG, "onUriChanged: ["+uri+"]");
            }
        };

        getContentResolver().registerContentObserver(new Uri.Builder().authority(SLAVE1_AUTHORITY).build(),
                true, mSyncObserver);
        getContentResolver().registerContentObserver(new Uri.Builder().authority(SLAVE2_AUTHORITY).build(),
                true, mSyncObserver);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mViewImplementation.bind(null);
        mViewImplementation.presenter(null);

        mPresenterImplementation.cancelSync();
        mPresenterImplementation.view(null);
        mPresenterImplementation.accounts(null);

        getContentResolver().unregisterContentObserver(mSyncObserver);
    }
}
