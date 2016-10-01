package com.example.moziliang.accountkeeper.accounts;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moziliang.accountkeeper.R;

import java.util.Arrays;

/**
 * Created by moziliang on 16/10/1.
 */
public class EditAccountDialog extends Dialog {

    private Context mContext;
    private static int mTheme = R.style.AccountDialog;

    private EditText accountNameEdittext;
    private EditText accountBalenceEdittext;
    private Button submitButton;
    private Button cancelButton;
    private Spinner currencyTypeSpinner;
    private String[] currencyTypeSpinnerOptions;
    private Button deleteButton;

    private String accountName = null;
    private String selectedCurrencyType = null;
    private String accountBalence;

    public AccountManager mAccountManager;

    public EditAccountDialog(Context context) {
        super(context, mTheme);
        this.mContext = context;
        mAccountManager = AccountManager.getInstance(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_account);


        setCancelable(false);

        initView();

        initSpinner();

        initButton();

        accountNameEdittext.setText(accountName);
        currencyTypeSpinner.setSelection(Arrays.asList(currencyTypeSpinnerOptions).indexOf(selectedCurrencyType));
        accountBalenceEdittext.setText(accountBalence);
    }

    private void initView() {
        accountNameEdittext = (EditText) findViewById(R.id.edit_account_name_edittext);
        accountNameEdittext.setEnabled(false);
        accountBalenceEdittext = (EditText) findViewById(R.id.edit_account_balence_edittext);
        submitButton = (Button) findViewById(R.id.edit_account_submit_button);
        cancelButton = (Button) findViewById(R.id.edit_account_cancel_button);
        currencyTypeSpinner = (Spinner) findViewById(R.id.edit_currency_type_spinner);
        deleteButton = (Button) findViewById(R.id.edit_account_delete_button);
    }

    private void initSpinner() {
        currencyTypeSpinnerOptions = mContext.getResources().getStringArray(R.array.currency_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                R.layout.dialog_spinner_text_item, currencyTypeSpinnerOptions);
        adapter.setDropDownViewResource(R.layout.dialog_spinner_text_item);
        currencyTypeSpinner.setAdapter(adapter);

        currencyTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCurrencyType = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCurrencyType = null;
            }
        });
    }

    private void initButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton.setEnabled(false);
                try{
                    accountName = accountNameEdittext.getText().toString();
                    if (accountName.equals("")) {
                        Toast.makeText(mContext, "Account Name is Empty", Toast.LENGTH_SHORT).show();
                        submitButton.setEnabled(true);
                        return;
                    }
                    if (selectedCurrencyType == null) {
                        Toast.makeText(mContext, "Currency Type is Empty", Toast.LENGTH_SHORT).show();
                        submitButton.setEnabled(true);
                        return;
                    }
                    String balenceString =  accountBalenceEdittext.getText().toString();
                    if (balenceString.equals("")) {
                        Toast.makeText(mContext, "Balence is Empty", Toast.LENGTH_SHORT).show();
                        submitButton.setEnabled(true);
                        return;
                    }
                    accountBalence = "" + Double.parseDouble(accountBalenceEdittext.getText().toString());

                    mAccountManager.EditAccountByName(accountName, selectedCurrencyType, accountBalence);

                    dismiss();
                } catch (Exception e) {
                    Toast.makeText(mContext, "Something wrong with Input Data", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    submitButton.setEnabled(true);
                    return;
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAccountManager.RemoveAccountByName(accountName, selectedCurrencyType, accountBalence);

                dismiss();
            }
        });
    }

    public void initTextData(String accountName, String currencyType, String balence) {
        this.accountName = accountName;
        this.selectedCurrencyType = currencyType;
        this.accountBalence = balence;
    }
}
