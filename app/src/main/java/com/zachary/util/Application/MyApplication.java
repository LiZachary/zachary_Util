package com.zachary.util.Application;

import com.amitshekhar.DebugDB;
import com.zachary.util.Util.LogUtil;
import com.zachary.util.Util.SharePreferenceUtil;

import org.litepal.LitePalApplication;

/**
 * Created by Zachary on 2018-02-27.
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
