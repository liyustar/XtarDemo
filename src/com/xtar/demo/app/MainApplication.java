package com.xtar.demo.app;

import android.app.Application;
import android.content.Context;

/**
 * 通过Application类就可以获得context的方法
 * 
 * @author lyx
 * 
 */
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
    public static Context getContext() {
        return mContext;
    }

}
