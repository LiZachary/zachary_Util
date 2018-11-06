package com.zachary.util.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zachary.util.BaseActivity.superActivity;
import com.zachary.util.Fragment.FragmentFrist;
import com.zachary.util.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Zachary
 * 首页，嵌套三个Fragment
 */
public class MainActivity extends superActivity {

    //首页头部布局
    @BindView(R.id.icon_left)
    ImageView icon_left;
    @BindView(R.id.icon_right)
    ImageView icon_right;
    @BindView(R.id.text_context)
    TextView text_context;

    @BindView(R.id.ll_bottom_iv_one)
    ImageView llBottomIvOne;
    @BindView(R.id.ll_bottom_tv_one)
    TextView  llBottomTvOne;

    @BindView(R.id.ll_bottom_iv_two)
    ImageView llBottomIvTwo;
    @BindView(R.id.ll_bottom_tv_two)
    TextView  llbottomTvTwo;

    @BindView(R.id.ll_bottom_iv_three)
    ImageView llBottomIvThree;
    @BindView(R.id.ll_bottom_tv_three)
    TextView  llBottomTvThree;

    @BindView(R.id.ll_bottom_rl_one)
    RelativeLayout llBottomRlOne;
    @BindView(R.id.ll_bottom_rl_two)
    RelativeLayout llBottomRlTwo;
    @BindView(R.id.ll_bottom_rl_three)
    RelativeLayout llBottomRlThree;

    @BindView(R.id.ll_bottom_tab)
    LinearLayout   llBottomTab;
    @BindView(R.id.content_layout)
    LinearLayout   contentLayout;
    @BindView(R.id.line)
    View           line;

    // 底部标签切换的Fragment
    private Fragment oneFragment, twoFragment, threeFragment, currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initTab();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    private void initView() {

    }

    //初始化底部标签
    private void initTab() {
        if (oneFragment == null) {
            oneFragment = new FragmentFrist();
        }
        text_context.setText("一");
        if (!oneFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, oneFragment, "1").commit();

            // 记录当前Fragment
            currentFragment = oneFragment;
            // 设置图片文本的变化
            llBottomIvOne.setImageResource(R.mipmap.bottom_home_click);
            llBottomTvOne.setTextColor(getResources()
                    .getColor(R.color.bottom_click));
            llBottomIvThree.setImageResource(R.mipmap.bottom_notice_normal);
            llBottomTvThree.setTextColor(getResources().getColor(
                    R.color.bottom_normal));
            llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_normal);
            llbottomTvTwo.setTextColor(getResources().getColor(
                    R.color.bottom_normal));
        }
    }

    //点击第一个tab
    private void clickTab1Layout() {

        if (oneFragment == null) {
            oneFragment = new FragmentFrist();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), oneFragment, "1");

        llBottomIvOne.setImageResource(R.mipmap.bottom_home_click);
        llBottomTvOne.setTextColor(getResources()
                .getColor(R.color.bottom_click));
        llBottomIvThree.setImageResource(R.mipmap.bottom_notice_normal);
        llBottomTvThree.setTextColor(getResources().getColor(
                R.color.bottom_normal));
        llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_normal);
        llbottomTvTwo.setTextColor(getResources().getColor(
                R.color.bottom_normal));
    }

    //点击第二个tab
    private void clickTab2Layout() {
        if (twoFragment == null) {
            twoFragment = new FragmentFrist();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), twoFragment, "2");

        llBottomIvOne.setImageResource(R.mipmap.bottom_home_normal);
        llBottomTvOne.setTextColor(getResources()
                .getColor(R.color.bottom_normal));
        llBottomIvThree.setImageResource(R.mipmap.bottom_notice_normal);
        llBottomTvThree.setTextColor(getResources().getColor(
                R.color.bottom_normal));
        llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_click);
        llbottomTvTwo.setTextColor(getResources().getColor(
                R.color.bottom_click));
    }

    //点击第三个tab
    private void clickTab3Layout() {
        if (threeFragment == null) {
            threeFragment = new FragmentFrist();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), threeFragment, "3");

        llBottomIvOne.setImageResource(R.mipmap.bottom_home_normal);
        llBottomTvOne.setTextColor(getResources()
                .getColor(R.color.bottom_normal));
        llBottomIvThree.setImageResource(R.mipmap.bottom_notice_click);
        llBottomTvThree.setTextColor(getResources().getColor(
                R.color.bottom_click));
        llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_normal);
        llbottomTvTwo.setTextColor(getResources().getColor(
                R.color.bottom_normal));
    }

    //添加或者显示碎片
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment, String tag) {

        if (currentFragment == fragment)
            return;

        // 如果当前fragment未被添加，则添加到Fragment管理器中
        if (!fragment.isAdded()) {
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment, tag).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    //切换底部fragment时，返回初始化状态
    private void reViewStatus(String s) {
        if (!currentFragment.getTag().equals(s)) {

        }
    }

    @OnClick({R.id.ll_bottom_rl_one, R.id.ll_bottom_rl_two, R.id.ll_bottom_rl_three,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_bottom_rl_one:
                icon_left.setVisibility(View.GONE);
                icon_right.setVisibility(View.GONE);
                text_context.setText("一");
                reViewStatus("1");
                clickTab1Layout();
                break;
            case R.id.ll_bottom_rl_two:
                icon_left.setVisibility(View.GONE);
                icon_right.setVisibility(View.GONE);
                text_context.setText("二");
                reViewStatus("2");
                clickTab2Layout();
                break;
            case R.id.ll_bottom_rl_three:
                icon_left.setVisibility(View.GONE);
                icon_right.setVisibility(View.GONE);
                text_context.setText("三");
                reViewStatus("3");
                clickTab3Layout();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 不保存onSaveInstanceState，即不执行super方法，使Activity失去fragment状态，使fragment的hide/show正常显示
        // 不然的话，调用hide/show 方法不会正常显示，不论底部怎么切换，一直停留 FirstFragment 页面。
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

