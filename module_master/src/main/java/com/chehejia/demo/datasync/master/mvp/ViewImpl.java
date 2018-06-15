package com.chehejia.demo.datasync.master.mvp;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chehejia.demo.datasync.master.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class ViewImpl implements Contract.View {

    private Contract.Presenter mPresenter = null;
    private Unbinder mUnbinder = null;

    @BindView(R.id.tv_console)
    public TextView mConsoleTV;

    @BindView(R.id.et_account)
    public EditText mAccountET;

    @BindView(R.id.et_syncAccount)
    public EditText mSyncTargetET;

    @BindView(R.id.btn_sync)
    public Button mSyncButton;

    @BindView(R.id.btn_addAccount)
    public Button mAddAccountButton;

    @OnClick(R.id.btn_sync)
    public void onSyncButtonPessed() {
        pressSyncButton();
    }

    @OnClick(R.id.btn_addAccount)
    public void onAddAccountButtonPressed() {
        pressAddAccountButton();
    }

    @OnTextChanged(R.id.et_account)
    public void onAddAccountTextChanged(CharSequence txt, int a, int b, int c) {
        mAddAccountButton.setEnabled(0 < txt.length());
    }

    @OnTextChanged(R.id.et_syncAccount)
    public void onSyncAccountTextChanged(CharSequence txt, int a, int b, int c) {
        mSyncButton.setEnabled(0 < txt.length());
    }

    @Override
    public Contract.Presenter presenter() {
        return mPresenter;
    }

    @Override
    public void presenter(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void bind(View root) {
        if (null != mUnbinder) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        if (null != root) {
            mUnbinder = ButterKnife.bind(this, root);
        }

        // view initialize
        onSyncAccountTextChanged("", 0, 0, 0);
        onAddAccountTextChanged("", 0, 0, 0);
    }

    @Override
    public void pressSyncButton() {
        String target = mSyncTargetET.getText().toString();
        if (0 < target.length()) {
            presenter().doSync(target);
        }
    }

    @Override
    public void pressAddAccountButton() {
        String account = mAccountET.getText().toString();
        if (0 < account.length()) {
            presenter().addAccount(account);
        }
    }

    @Override
    public void displayWords(String words) {

    }

    @Override
    public void enableSyncButton(boolean value) {

    }
}
