package com.zachary.utli.Application;

import com.amitshekhar.DebugDB;
import com.zachary.utli.Util.LogUtil;
import com.zachary.utli.Util.SharePreferenceUtil;

import org.litepal.LitePalApplication;

/**
 * Created by Zachary on 2017-02-27.
 */

public class MyApplication  extends LitePalApplication {

    protected static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance =this;

        LogUtil.e(DebugDB.getAddressLog());

        SharePreferenceUtil.initSharePreferenceUtil(getApplicationContext());
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
