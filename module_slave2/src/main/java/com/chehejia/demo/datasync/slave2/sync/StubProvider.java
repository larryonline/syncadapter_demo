package com.chehejia.demo.datasync.slave2.sync;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class StubProvider extends ContentProvider {

    private static final String TAG = "SyncProvider_SLAVE2";

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query: URI["+uri+"] projection["+projection+"] selection["+selection+"] selectionArgs["+selectionArgs+"] sortOrder["+sortOrder+"]");
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, "getType: URI["+uri+"]");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert: URI["+uri+"] ContentValues["+values+"]");
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete: URI["+uri+"] selection["+selection+"] selectionArgs["+selectionArgs+"]");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete: URI["+uri+"] ContentValues["+values+"] selection["+selection+"] selectionArgs["+selectionArgs+"]");
        return 0;
    }
}
