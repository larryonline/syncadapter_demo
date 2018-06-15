package com.chehejia.demo.datasync.slave2.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class SyncService extends Service {

    static SyncAdapter sAdapter = null;

    static final Object sAdapterLock = new Object();

    @Override
    public void onCreate() {
        super.onCreate();

        synchronized (sAdapterLock) {
            if (null == sAdapter) {
                sAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sAdapter.getSyncAdapterBinder();
    }
}
