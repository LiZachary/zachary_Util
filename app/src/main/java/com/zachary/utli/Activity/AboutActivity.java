package com.zachary.utli.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zachary.utli.BaseActivity.ToolBarActivity;
import com.zachary.utli.BaseActivity.superActivity;
import com.zachary.utli.R;
import com.zachary.utli.Util.DialogUtil;
import com.zachary.utli.Util.ResourceUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutActivity extends ToolBarActivity {

    @BindView(R.id.tv_activity_about_licenses)
    TextView tvActivityAboutLicenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        getToolbarTitle().setText("关于");
        getSubTitle().setVisibility(View.GONE);
        getSub2Title().setVisibility(View.GONE);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_about;
    }

    @OnClick(R.id.tv_activity_about_licenses)
    public void onClick() {
        DialogUtil.showAlertDialogText(AboutActivity.this, "Zachary", ResourceUtil.getString(R.string.license));
    }
}
