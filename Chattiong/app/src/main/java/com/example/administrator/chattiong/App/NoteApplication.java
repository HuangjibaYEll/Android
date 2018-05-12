package com.example.administrator.chattiong.App;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.baidu.mapapi.SDKInitializer;
import org.litepal.LitePalApplication;
import cn.bmob.v3.Bmob;



public class NoteApplication extends Application {
    private static NoteApplication mUtils;
    private static Context context;
    private SharedPreferences mSp;


    public synchronized static NoteApplication getUtils() {
        return mUtils;
    }

    //创建缓存
    public synchronized SharedPreferences getSpUtil() {
        if (mSp == null) {
            mSp = PreferenceManager.getDefaultSharedPreferences(this);
        }
        return mSp;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mUtils = this;
        init();
    }


    public void init() {
        //初始化bmob云服务器
        context = getApplicationContext();
        Bmob.initialize(this, "c742926499ab506b36d43b02ce34fc1e");
        mSp = PreferenceManager.getDefaultSharedPreferences(this);
        LitePalApplication.initialize(context);
        SDKInitializer.initialize(context);
    }




    //获取context
    public static Context getContext() {
        return context;
    }
}
