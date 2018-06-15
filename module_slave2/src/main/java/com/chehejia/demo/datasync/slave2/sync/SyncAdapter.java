package com.chehejia.demo.datasync.slave2.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String TAG = "SyncAdapterSlave2";

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {


        Log.d(TAG, "\n");
        Log.d(TAG, "\n");
        Log.d(TAG, "-------------------------------------------");
        Log.d(TAG, "slave2 start sync: " + account.toString());
        Log.d(TAG, "-------------------------------------------");
        Log.d(TAG, "\n");
        Log.d(TAG, "\n");

//        ContentValues values = new ContentValues();
//        values.put("user_name", "larry");
//        values.put("age", 18);

//        Uri insert = new Uri.Builder()
//                .authority("com.chehejia.demo.account")
//                .appendPath("user")
//                .appendPath("profile")
//                .appendPath("insert")
//                .build();

//        provider.insert(insert, values)
//        provider.update()
//        provider.delete()



    }
}
