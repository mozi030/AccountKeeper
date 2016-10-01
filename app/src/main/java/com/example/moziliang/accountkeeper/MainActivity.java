package com.example.moziliang.accountkeeper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.moziliang.accountkeeper.accounts.AccountDataFormat;
import com.example.moziliang.accountkeeper.accounts.AccountsFragment;
import com.example.moziliang.accountkeeper.bills.BillsFragment;
import com.example.moziliang.accountkeeper.database.DataBaseManager;
import com.example.moziliang.accountkeeper.database.MySQLiteOpenHelper;
import com.example.moziliang.accountkeeper.summary.SummaryFragment;
import com.example.moziliang.accountkeeper.utils.ConstantValues;
import com.example.moziliang.accountkeeper.utils.DataFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AccountsFragment.OnFragmentInteractionListener,
        BillsFragment.OnFragmentInteractionListener, SummaryFragment.OnFragmentInteractionListener {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private int bottomButtonIds[] = new int[]{R.id.bills_button, R.id.accounts_button, R.id.summary_button};
    private Button bottomButton[] = new Button[bottomButtonIds.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initViewPager();


    }

    private void initView() {
        for (int i = 0; i < bottomButton.length; i++) {
            bottomButton[i] = (Button)findViewById(bottomButtonIds[i]);
            bottomButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private void initViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mFragments = new ArrayList<>(Arrays.asList(
                BillsFragment.newInstance(), AccountsFragment.newInstance(this), SummaryFragment.newInstance()));

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(ConstantValues.debugTab, "onFragmentInteraction: " + uri.toString());
    }

}
