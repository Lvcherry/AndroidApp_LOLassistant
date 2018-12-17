package com.ryan.newsapp.ui;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ShareSDK.initSDK(this);
    }
}
