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

/**
 * Created by moziliang on 16/9/11.
 */
public class CreateAccountDialog extends Dialog {

    private Context mContext;
    private static int mTheme = R.style.AccountDialog;

    private EditText accountNameEdittext;
    private EditText accountBalenceEdittext;
    private Button submitButton;
    private Button cancelButton;
    private Spinner currencyTypeSpinner;

    private String accountName = null;
    private String selectedCurrencyType = null;
    private String accountBalence;

    public AccountManager mAccountManager;

    public CreateAccountDialog(Context context) {
        super(context, mTheme);
        this.mContext = context;
        mAccountManager = AccountManager.getInstance(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_create_new_account);


        setCancelable(false);

        initView();

        initSpinner();

        initButton();
    }

    private void initView() {
        accountNameEdittext = (EditText) findViewById(R.id.add_account_name_edittext);
        accountBalenceEdittext = (EditText) findViewById(R.id.add_account_balence_edittext);
        submitButton = (Button) findViewById(R.id.add_account_submit_button);
        cancelButton = (Button) findViewById(R.id.add_account_cancel_button);
        currencyTypeSpinner = (Spinner) findViewById(R.id.add_currency_type_spinner);
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                R.layout.dialog_spinner_text_item, mContext.getResources().getStringArray(R.array.currency_array));
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

                    mAccountManager.AddAccount(accountName, selectedCurrencyType, accountBalence);

                    dismiss();
                } catch (Exception e) {
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
    }
}
