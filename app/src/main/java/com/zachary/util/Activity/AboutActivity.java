package com.zachary.util.Activity;

import android.os.Bundle;
import android.widget.TextView;
import com.zachary.util.BaseActivity.superActivity;
import com.zachary.util.R;
import com.zachary.util.Util.DialogUtil;
import com.zachary.util.Util.ResourceUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Zachary
 */
public class AboutActivity extends superActivity {

    @BindView(R.id.tv_activity_about_licenses)
    TextView tvActivityAboutLicenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
