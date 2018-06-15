package com.chehejia.demo.datasync.slave1.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String TAG = "SyncAdapterSlave1";

    private static final String AUTHORITY = "com.chehejia.demo.account.slave1";
    private static final String PATH = "sync";
    private static final String SYNC_STATE_QUERY_KEY = "state";
    private static final String SYNC_STATE_QUERY_VAL_START = "start";
    private static final String SYNC_STATE_QUERY_VAL_COMPLETE = "complete";
    private static final String SYNC_STATE_QUERY_VAL_FAILED = "failed";

    private static final String SYNC_IS_FIRSTTIME_QUERY_KEY = "firsttime";
    private static final int SYNC_IS_FIRSTTIME_QUERY_VAL_NO = 0;
    private static final int SYNC_IS_FIRSTTIME_QUERY_VAL_YES = 1;


    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, final SyncResult syncResult) {

        final ContentResolver resolver = getContext().getContentResolver();
        final Uri.Builder uriBuilder = new Uri.Builder().authority(AUTHORITY).appendPath(PATH);


        Log.d(TAG, "SLAVE1 SYNC START: " + account.toString());

        try{
            resolver.notifyChange(uriBuilder
                    .clearQuery()
                    .appendQueryParameter(SYNC_STATE_QUERY_KEY, SYNC_STATE_QUERY_VAL_START)
                    .build(), null);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.chehejia.com")
                    .build();

            ChehejiaGateway gateway = retrofit.create(ChehejiaGateway.class);

            Call<ResponseBody> call = gateway.getHomePage();
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d(TAG, "SLAVE1 SYNC COMPLETE");
//                    syncResult.stats.numUpdates ++;

                    resolver.notifyChange(uriBuilder
                            .clearQuery()
                            .appendQueryParameter(SYNC_STATE_QUERY_KEY, SYNC_STATE_QUERY_VAL_COMPLETE)
                            .build(), null);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
//                    syncResult.stats.numIoExceptions ++;
                    Log.d(TAG, "SLAVE1 SYNC FAILED");

                    resolver.notifyChange(uriBuilder
                            .clearQuery()
                            .appendQueryParameter(SYNC_STATE_QUERY_KEY, SYNC_STATE_QUERY_VAL_FAILED)
                            .build(), null);
                }
            });
        } catch (Throwable e) {

            StackTraceElement[] sts = e.getStackTrace();
            for (StackTraceElement st : sts) {
                Log.e(TAG, st.toString());
            }
            e.printStackTrace();
        }

    }
}
