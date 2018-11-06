package com.zachary.utli.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zachary.utli.BaseActivity.ToolBarActivity;
import com.zachary.utli.R;
import com.zachary.utli.Util.GlideUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends ToolBarActivity {

    @BindView(R.id.ll_clear_imagecache)
    LinearLayout llClearImagecache;

    @BindView(R.id.tv_imagecachesize)
    TextView     tvImagecachesize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inITview();
    }

    private void inITview() {
        getToolbarTitle().setText("设置");
        tvImagecachesize.setText(GlideUtil.getInstance().getCacheSize(SettingActivity.this));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_setting;
    }

    @OnClick({R.id.ll_clear_imagecache})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_clear_imagecache:
                GlideUtil.getInstance().clearImageAllCache(SettingActivity.this);
                //修改，设置缓存
                String size=GlideUtil.getInstance().getCacheSize(SettingActivity.this);
                tvImagecachesize.setText(size);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
