package com.zachary.utli.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.zachary.utli.BaseActivity.ToolBarActivity;
import com.zachary.utli.R;

public class SwitchModeActivity extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initview();
    }
    @Override
    public int getContentViewId() {
        return R.layout.activity_switch_mode;
    }

    private void initview() {
        //设置500毫秒后执行当前activity的销毁操作
        getToolbarTitle().setText("查询");
        getToolbar().setNavigationIcon(getResources().getDrawable(R.mipmap.home_menu_48));
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //execute the task
                back(null);
            }
        }, 300);
    }

    public void back(View v) {
        this.finish();
        try {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.switchmode_activity_in, R.anim.switchmode_activity_out);
        } catch (Exception e) {
        }
    }
    //按返回键
    @Override
    public void onBackPressed() {
        back(null);
    }
}
