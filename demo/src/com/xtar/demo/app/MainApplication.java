package com.xtar.demo.app;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    private static Context mContext = null; // 全局上下文

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }

    /**
     * 获取全局上下文
     * 
     * @return mContext
     */
    public Context getContext() {
        return mContext;
    }

}
