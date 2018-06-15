package com.chehejia.demo.datasync.master.mvp;

import android.accounts.AccountManager;
import android.content.Context;

public interface Contract {


    interface View {

        Presenter presenter();
        void presenter(Presenter presenter);

        void bind(android.view.View root);

        void pressSyncButton();
        void pressAddAccountButton();

        void displayWords(String words);

        void enableSyncButton(boolean value);
    }

    interface Presenter {

        View view();
        void view(View view);

        AccountManager accounts();
        void accounts(AccountManager am);

        void addAccount(String accountName);

        void doSync(String accountName);

        void cancelSync();

    }
}
