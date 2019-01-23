package com.zachary.util.Activity;

import android.os.Bundle;
import android.webkit.WebView;
import com.zachary.util.BaseActivity.SuperActivity;
import com.zachary.util.R;
import butterknife.BindView;

/**
 * @author zachary
 * web
 */
public class WebActivity extends SuperActivity {

    @BindView(R.id.web_view)
    WebView web_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_web;
    }
}
