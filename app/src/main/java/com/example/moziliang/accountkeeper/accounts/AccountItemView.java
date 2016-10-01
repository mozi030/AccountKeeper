package com.example.moziliang.accountkeeper.accounts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.moziliang.accountkeeper.R;

/**
 * Created by moziliang on 16/10/1.
 */
public class AccountItemView extends FrameLayout {
    private TextView accountNameTextView;
    private TextView currencyTypeTextView;
    private TextView balenceTextView;
    private Button accountEditButton;

    private String accountName = "";
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
        accountNameTextView.setText(accountName);
    }

    private String currencyType = "";
    public String getCurrencyType() {
        return currencyType;
    }
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
        currencyTypeTextView.setText(currencyType);
    }

    private String balence = "";
    public String getBalence() {
        return balence;
    }
    public void setBalence(String balence) {
        this.balence = balence;
        balenceTextView.setText(balence);
    }


    public AccountItemView(Context context) {
        super(context);
        init(context);
    }
    public AccountItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public AccountItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.account_item_view, this);
        accountNameTextView = (TextView) findViewById(R.id.account_name_textview);
        currencyTypeTextView = (TextView) findViewById(R.id.currency_type_text_view);
        balenceTextView = (TextView) findViewById(R.id.balence_text_view);
        accountEditButton = (Button) findViewById(R.id.account_edit_button);
    }

    public Button getAccountEditButton() {
        return accountEditButton;
    }
}
