package com.zachary.util.BaseActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Window;

import com.zachary.util.R;
import com.zachary.util.Util.LogUtil;
import com.zachary.util.Util.SharePreferenceUtil;
import butterknife.ButterKnife;

public abstract class SuperActivity extends AppCompatActivity {

    protected  boolean enableNightMode ;
    private int width;
    private int height;
    private int statusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableNightMode= SharePreferenceUtil.getBooleanSP("enableNightMode");
        if(!enableNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        LogUtil.e(this.getClass().getName()+"--->onCreate");
    }
    public abstract int getContentViewId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e(this.getClass().getName()+"--->onDestroy");
    }
}
