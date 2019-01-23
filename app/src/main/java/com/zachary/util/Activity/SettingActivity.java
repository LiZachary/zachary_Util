package com.zachary.util.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zachary.util.BaseActivity.SuperActivity;
import com.zachary.util.R;
import com.zachary.util.Util.GlideUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Zachary
 */
public class SettingActivity extends SuperActivity {

    @BindView(R.id.ll_clear_imagecache)
    LinearLayout llClearImagecache;

    @BindView(R.id.tv_imagecachesize)
    TextView     tvImagecachesize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

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
