package com.example.moziliang.accountkeeper.accounts;

import android.content.Context;

import com.example.moziliang.accountkeeper.database.DataBaseManager;
import com.example.moziliang.accountkeeper.utils.ConstantValues;
import com.example.moziliang.accountkeeper.utils.DataFormat;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by moziliang on 16/9/11.
 */
public class AccountManager {

    static private AccountManager mAccountManager = null;

    static public AccountManager getInstance(Context context) {
        if (mAccountManager == null) {
            mAccountManager = new AccountManager(context);
        }
        return mAccountManager;
    }

    private DataBaseManager mDataBaseManager;
    private Context mContext;
    private AccountManager(Context context) {
        mContext = context;

        AccountDataFormat.setDatabaseTableName();
        AccountDataFormat.setAllDataNames();

        mDataBaseManager = DataBaseManager.getInstance(mContext);
        mDataBaseManager.createTable(AccountDataFormat.getDatabaseTableName(), AccountDataFormat.getAllDataNames());
    }

    public ArrayList<DataFormat> getAllAccount() {
        return mDataBaseManager.getAllRows(AccountDataFormat.getDatabaseTableName(), AccountDataFormat.getAllDataNames());
    }

    public boolean AddAccount(String accountName, String currencyType, String balence) {
        return mDataBaseManager.insert(new AccountDataFormat(accountName, currencyType, balence), AccountDataFormat.KEY_ACCOUNT_NAME);
    }

    public boolean RemoveAccountByName(String accountName, String currencyType, String balence) {
        return mDataBaseManager.remove(new AccountDataFormat(accountName, currencyType, balence), AccountDataFormat.KEY_ACCOUNT_NAME);
    }

    public boolean EditAccountByName(String accountName, String currencyType, String balence) {
        return mDataBaseManager.update(new AccountDataFormat(accountName, currencyType, balence), AccountDataFormat.KEY_ACCOUNT_NAME);
    }

}
