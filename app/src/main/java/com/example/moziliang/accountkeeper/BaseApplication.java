package com.example.moziliang.accountkeeper;

import android.app.Application;
import android.content.Context;

/**
 * Created by moziliang on 16/9/11.
 */
public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
