package com.example.moziliang.accountkeeper.accounts;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.moziliang.accountkeeper.R;
import com.example.moziliang.accountkeeper.database.DataBaseManager;
import com.example.moziliang.accountkeeper.utils.ConstantValues;
import com.example.moziliang.accountkeeper.utils.DataFormat;

import java.util.ArrayList;
import java.util.List;

public class AccountsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    static private Context mContext = null;

    private LinearLayout accountLinearLayout;

    private AccountManager mAccountManager;

    public AccountsFragment() {
    }

    public static AccountsFragment newInstance(Context context) {
//        mContext = context;
        AccountsFragment fragment = new AccountsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mContext == null) {
            mContext = getActivity();
        }
        mAccountManager = AccountManager.getInstance(mContext);

        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_accounts, container, false);

        Button createAccountButton = (Button) layout.findViewById(R.id.create_account_button);
        createAccountButton.setOnClickListener(new CreateAccountButtonOnClickListener());

        initAccountList(layout);

//        ArrayList<DataFormat> datas = DataBaseManager.getInstance(mContext).getAllRows(AccountDataFormat.getDatabaseTableName(), AccountDataFormat.getAllDataNames());
//
//        for (DataFormat data : datas) {
//            Log.d(ConstantValues.debugTab,
//                    "here: " + data.getValueByName(AccountDataFormat.KEY_ACCOUNT_NAME
//                            + " " + data.getValueByName(AccountDataFormat.KEY_CURRENCY_TYPE
//                            + " " + data.getValueByName(AccountDataFormat.KEY_BALANCE))));
//        }

        return layout;
    }

    private class CreateAccountButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            CreateAccountDialog createAccountDialog = (new CreateAccountDialog(mContext));
            createAccountDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    refreshAccountList();
                }
            });
            createAccountDialog.show();
        }
    }

    private void initAccountList(View layout) {
        accountLinearLayout = (LinearLayout) layout.findViewById(R.id.account_linearlayout);
        refreshAccountList();
    }

    private void refreshAccountList() {
        accountLinearLayout.removeAllViews();
        ArrayList<DataFormat> allAccounts = mAccountManager.getAllAccount();
        for (DataFormat account : allAccounts) {
            AccountItemView accountItemView = new AccountItemView(mContext);
            accountItemView.setAccountName(account.getValueByName(AccountDataFormat.KEY_ACCOUNT_NAME));
            accountItemView.setCurrencyType(account.getValueByName(AccountDataFormat.KEY_CURRENCY_TYPE));
            accountItemView.setBalence(account.getValueByName(AccountDataFormat.KEY_BALANCE));
            accountLinearLayout.addView(accountItemView);

            Button accountEditButton = accountItemView.getAccountEditButton();
            accountEditButton.setOnClickListener(
                    new EditAccountButtonOnClickListener(
                            accountItemView.getAccountName(), accountItemView.getCurrencyType(), accountItemView.getBalence()));
        }
    }

    private class EditAccountButtonOnClickListener implements View.OnClickListener {
        private String accountName;
        private String currencyType;
        private String balence;

        public EditAccountButtonOnClickListener(String accountName, String currencyType, String balence) {
            this.accountName = accountName;
            this.currencyType = currencyType;
            this.balence = balence;
        }
        @Override
        public void onClick(View v) {
            EditAccountDialog editAccountDialog = new EditAccountDialog(mContext);
            editAccountDialog.initTextData(accountName, currencyType, balence);
            editAccountDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    refreshAccountList();
                }
            });
            editAccountDialog.show();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
