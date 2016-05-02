package com.wuyin.supermarket.application;

import android.app.Application;

/**
 * Created by wuyin on 2016/5/2.
 */
public class BaseApplication extends Application {

    private static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static BaseApplication getmApplication() {
        return mApplication;
    }
}
