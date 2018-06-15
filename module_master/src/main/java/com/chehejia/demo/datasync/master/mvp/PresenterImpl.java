package com.chehejia.demo.datasync.master.mvp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.SyncAdapterType;
import android.content.SyncRequest;
import android.os.Bundle;

public class PresenterImpl implements Contract.Presenter {

    private static final String ACCOUNT_TYPE = "com.chehejia.demo.account";

    private Contract.View mView = null;
    private AccountManager mAccounts = null;

    @Override
    public Contract.View view() {
        return mView;
    }

    @Override
    public void view(Contract.View view) {
        mView = view;
    }

    @Override
    public AccountManager accounts() {
        return mAccounts;
    }

    @Override
    public void accounts(AccountManager am) {
        mAccounts = am;
    }

    @Override
    public void addAccount(String accountName) {
        Account account = new Account(accountName, ACCOUNT_TYPE);
        accounts().addAccountExplicitly(account, null, null);
    }

    @Override
    public void doSync(String accountName) {
        Account account = new Account(accountName, ACCOUNT_TYPE);
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

        SyncAdapterType[] adapters = ContentResolver.getSyncAdapterTypes();

        // sync data globally
        for (SyncAdapterType adapter : adapters) {
            if (ACCOUNT_TYPE.equals(adapter.accountType)) {
                ContentResolver.requestSync(account, adapter.authority, bundle);
            }
        }
    }

    @Override
    public void cancelSync() {
        // DO CANCEL.
    }

    // another implementation of ContentResolver.getSyncAdapterTypes()
//    private List<String> getAuthoritiesForAccountData() {
//        PackageManager pms = context().getPackageManager();
//        PackageInfo myPackage = null;
//        try {
//            myPackage = pms.getPackageInfo(context().getPackageName(), PackageManager.GET_SIGNATURES);
//        } catch (Exception e) {
//
//        }
//        if (null == myPackage) {
//            Log.e("SyncAdapter", "Can not retrieve package info.");
//            return null;
//        }
//
////        Log.d("SyncAdapter", "signatures: " + myPackage.signatures.toString());
//
//        List<String> ret = new ArrayList<>();
//
//        List<PackageInfo> packages = pms.getInstalledPackages(PackageManager.GET_SERVICES | PackageManager.GET_META_DATA | PackageManager.GET_SIGNATURES);
//        for (PackageInfo info : packages) {
//            if (myPackage.sharedUserId.equals(info.sharedUserId)) {
////                Log.d("SyncAdapter", "  |-> signatures if equals: " + Arrays.equals(myPackage.signatures, info.signatures));
//                if (null != info.services) {
//                    for (ServiceInfo service : info.services)
//                    {
//                        Bundle meta = service.metaData;
//                        if (null != meta) {
//                            int resId = meta.getInt("android.content.SyncAdapter");
//
//                            try {
//                                Resources resources = pms.getResourcesForApplication(info.applicationInfo);
//                                XmlResourceParser parser = resources.getXml(resId);
//                                String authority = getContentAuthority(parser);
//                                ret.add(authority);
//                                parser.close();
//
////                                Log.d("SyncAdapter", "found authority: " + authority);
//                            } catch (Exception e) {
//
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return ret;
//    }
//
//    private String getContentAuthority(XmlResourceParser parser) throws Exception {
//        String ret = null;
//
//        while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {
//            if (parser.getEventType() == XmlResourceParser.START_TAG) {
//
//                final String NS = "http://schemas.android.com/apk/res/android";
//                final String NAME = "contentAuthority";
//
//
////                Log.d("SyncAdapter", "xml namespace: " + parser.getNamespace());
////                int count = parser.getAttributeCount();
////                for (int i = 0; i < count; i++) {
////                    String value = parser.getAttributeValue(i);
////                    String name = parser.getAttributeName(i);
////                    String ns = parser.getAttributeNamespace(i);
////
////                    Log.d("SyncAdapter", "" + ns + ":" + name + "=" + value);
////                }
//
//                if ("sync-adapter".equals(parser.getName())) {
//                    ret = parser.getAttributeValue(NS, NAME);
//                }
//            }
//
//            parser.next();
//        }
//        return ret;
//    }
}
