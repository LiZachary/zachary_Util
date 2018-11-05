package com.zachary.utli.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zachary.utli.Activity.slide_Left.adapter.Side_LeftAdapter;
import com.zachary.utli.Activity.slide_Left.data.Left_itemdata;
import com.zachary.utli.BaseActivity.ToolBarActivity;

import com.zachary.utli.BaseAdapter.BaseListView.ViewCreator;
import com.zachary.utli.BaseBean.SlideLeftBean;
import com.zachary.utli.Fragment.FragmentFrist;
import com.zachary.utli.R;
import com.zachary.utli.Util.DialogUtil;
import com.zachary.utli.Util.ResourceUtil;
import com.zachary.utli.Util.SharePreferenceUtil;
import com.zachary.utli.View.CustomRelativeLayout;
import com.zachary.utli.View.DragLayout;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends ToolBarActivity implements ViewCreator<SlideLeftBean, Side_LeftAdapter.SlideLeftHolder> {

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
    @BindView(R.id.toolbar_subtitle)
    TextView       toolbarSubtitle;
    @BindView(R.id.ll_bottom_tab)
    LinearLayout   llBottomTab;
    @BindView(R.id.content_layout)
    LinearLayout   contentLayout;
    @BindView(R.id.line)
    View           line;
    @BindView(R.id.toolbar_sub2title)
    TextView       toolbarSub2title;
    @BindView(R.id.iv_bottom)
    ImageView      ivBottom;
    @BindView(R.id.lv)
    ListView       lv;
    @BindView(R.id.dl)
    DragLayout     dl;
    @BindView(R.id.left_name)
    TextView       leftName;
    @BindView(R.id.ll_left_setting)
    LinearLayout   llLeftSetting;
    @BindView(R.id.ll_left_night)
    LinearLayout   llLeftNight;
    @BindView(R.id.tv_left_weather)
    TextView       tvLeftWeather;
    @BindView(R.id.tv_left_city)
    TextView       tvLeftCity;
    @BindView(R.id.tv_left_night)
    TextView       tvleftnight;
    @BindView(R.id.crl)
    CustomRelativeLayout crl;
    @BindView(R.id.rl_left_bg)
    RelativeLayout rlleftbg;

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
        if(enableNightMode){
            tvleftnight.setText("夜间");
            rlleftbg.setBackgroundResource(R.drawable.sidebar_bg_night);
        }else{
            tvleftnight.setText("白天");
            rlleftbg.setBackgroundResource(R.drawable.sidebar_bg);
        }
        getToolbar().setNavigationIcon(getResources().getDrawable(R.mipmap.home_menu_48));
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl.open();
            }
        });
        Side_LeftAdapter mAdapter = new Side_LeftAdapter(Left_itemdata.getItemBeans(), this);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        //意见反馈
                        DialogUtil.showAlertDialogText(MainActivity.this, "意见反馈", ResourceUtil.getString(R.string.suggestback));
                        break;
                    case 1:
                        //分享

                        break;
                    case 2:
                        //关于
                        startActivity(new Intent(MainActivity.this,AboutActivity.class));
                        break;
                    case 3:
                        //退出登录
                        Glide.with(MainActivity.this)
                                .load(R.drawable.left_image)
                                .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                                .into(ivBottom);
                        leftName.setText("点击登录");
                        leftName.setClickable(true);
                        break;

                }
            }
        });
    }

    /**
     * 初始化底部标签
     */
    private void initTab() {
        if (oneFragment == null) {
            oneFragment = new FragmentFrist();
        }
        getToolbarTitle().setText("一");
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

    /**
     * 点击第一个tab
     */
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

    /**
     * 点击第二个tab
     */
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


    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment, String tag) {

        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment, tag).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    /**
     * 切换底部fragment时，订单页面(fragment)返回初始化状态
     */
    private void reViewStatus(String s) {
        if (!currentFragment.getTag().equals(s)) {

        }
    }

    @Override
    protected boolean isShowBacking() {
        return false;
    }

    @Override
    public Side_LeftAdapter.SlideLeftHolder createHolder(int position, ViewGroup parent) {
        return new Side_LeftAdapter.SlideLeftHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.left_item_layout, parent, false));
    }

    @Override
    public void bindData(int position, Side_LeftAdapter.SlideLeftHolder holder, SlideLeftBean data) {
        holder.iv.setImageResource(data.getImg());
        holder.text.setText(data.getTitle());
    }


    @OnClick({R.id.ll_bottom_rl_one, R.id.ll_bottom_rl_two, R.id.ll_bottom_rl_three,
            R.id.toolbar_subtitle, R.id.toolbar_sub2title, R.id.left_name,R.id.ll_left_setting, R.id.ll_left_night})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_bottom_rl_one:
                getSubTitle().setVisibility(View.GONE);
                getSub2Title().setVisibility(View.GONE);
                getToolbarTitle().setText("一");
                reViewStatus("1");
                clickTab1Layout();
                break;
            case R.id.ll_bottom_rl_two:
                getSubTitle().setVisibility(View.VISIBLE);
                getToolbarTitle().setText("二");
                getSubTitle().setText("编辑");
                reViewStatus("2");
                clickTab2Layout();
                break;
            case R.id.ll_bottom_rl_three:
                getSubTitle().setVisibility(View.GONE);
                getSub2Title().setVisibility(View.GONE);
                getToolbarTitle().setText("三");
                reViewStatus("3");
                clickTab3Layout();
                break;

            case R.id.toolbar_subtitle:
                if ("编辑".equals(getSubTitle().getText().toString())) {
                    getSubTitle().setText("取消");
                    getSub2Title().setVisibility(View.VISIBLE);
                    getSub2Title().setText("全选");

                } else if ("取消".equals(getSubTitle().getText().toString())) {
                    getSubTitle().setText("编辑");
                    getSub2Title().setVisibility(View.GONE);

                }
                break;
            case R.id.toolbar_sub2title:
                if ("全选".equals(getSub2Title().getText().toString())) {
                    getSub2Title().setText("取消全选");

                } else if ("取消全选".equals(getSub2Title().getText().toString())) {
                    getSub2Title().setText("全选");

                }
                break;
            case R.id.left_name:
                //开始qq授权登录
                break;
            case R.id.ll_left_setting:
                //设置
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            case R.id.ll_left_night:
                //夜间模式
                if(tvleftnight.getText().toString().equals("夜间")){
                    SharePreferenceUtil.setBooleanSP("enableNightMode",false);
                    tvleftnight.setText("白天");
                    setEnableNightMode(false,crl);
                }else{
                    SharePreferenceUtil.setBooleanSP("enableNightMode",true);
                    tvleftnight.setText("夜间");
                    setEnableNightMode(true,crl);
                }
                break;
        }
    }


    //如果在 MainActivity 上返回首页，则不需要Intent ，而是切换到FirstFragment
    @Override
    protected void backhome() {
             getSubTitle().setVisibility(View.GONE);
             getSub2Title().setVisibility(View.GONE);
             getToolbarTitle().setText("一");
             reViewStatus("1");
             clickTab1Layout();
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

