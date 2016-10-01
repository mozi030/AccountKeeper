package com.example.moziliang.accountkeeper.accounts;

import com.example.moziliang.accountkeeper.utils.DataFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by moziliang on 16/10/1.
 */
public class AccountDataFormat extends DataFormat {
    public static String KEY_ACCOUNT_NAME = "ACCOUNT_NAME";
    public static String KEY_CURRENCY_TYPE = "CURRENCY_TYPE";
    public static String KEY_BALANCE = "BALANCE";

    static public void setDatabaseTableName() {
        setDatabaseTableName("ACCOUNT");
    }
    static public void setAllDataNames() {
        setAllDataNames(new ArrayList<String>(Arrays.asList(KEY_ACCOUNT_NAME, KEY_CURRENCY_TYPE, KEY_BALANCE)));
    }

    public AccountDataFormat(String accountName, String currencyType, String balence) {
        setValueByName(KEY_ACCOUNT_NAME, accountName);
        setValueByName(KEY_CURRENCY_TYPE, currencyType);
        setValueByName(KEY_BALANCE, balence);
    }
}
