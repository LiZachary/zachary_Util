package com.zachary.utli.Fragment.home.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zachary.utli.BaseFragment.BaseFragment;
import com.zachary.utli.R;
import com.zachary.utli.Util.ConstantUtil;
import com.zachary.utli.View.EmptyViewLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Zachary
 *
 */
public class FragmentFrist extends BaseFragment {

    @BindView(R.id.content)
    RelativeLayout content;
    @BindView(R.id.emptyView)
    EmptyViewLayout emptyView;

    private Activity mActivity;

    //设置根布局资源id
    @Override
    public int getContentViewId() {
        return R.layout.fragment_first;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        emptyView.bindView(content);// 绑定要显示的View
        emptyView.buttonClick(this, "query");// 加载失败后点击的时候执行onload方法
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    //点击事件
    @OnClick({R.id.content})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.content:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** 二维码扫描 */
        if (resultCode == mActivity.RESULT_OK
                && requestCode == ConstantUtil.REQUEST_QR_CODE
                && data != null) {
            String result = data.getStringExtra("result");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity=activity;
    }
}
