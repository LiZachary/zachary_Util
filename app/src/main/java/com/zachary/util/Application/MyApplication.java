package com.zachary.util.Application;

import android.Manifest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.zachary.util.Model.UserInfoBean;
import com.zachary.util.Net.RetrofitSingleton;
import com.zachary.util.Net.http.OkHttpApi;
import com.zachary.util.Net.provider.ProviderPool;
import com.zachary.util.Profile.UserProfile;
import com.zachary.util.Util.DeviceUtil;
import com.zachary.util.Util.SharePreferenceUtil;
import org.litepal.LitePalApplication;
import java.util.Map;

/**
 * Created by Zachary on 2018-02-27.
 * Application
 */
public class MyApplication extends LitePalApplication {

    protected static MyApplication instance;
    public static OkHttpApi okHttpApi;
    public UserProfile userProfile;

    @Override
    public void onCreate() {
        super.onCreate();
        instance =this;

        //LogUtil.e(DebugDB.getAddressLog());

        SharePreferenceUtil.initSharePreferenceUtil(getApplicationContext());
        initHeaderInfo();
    }

    private void initHeaderInfo(){
        String version = "";
        Map<String,String> paramMap = ProviderPool.getParamMap();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

            paramMap.put("deviceId", DeviceUtil.getDeviceId(this));
        }
        paramMap.put("client","Android");
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            version = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        paramMap.put("version",version);
    }

    public static void setOkHttpNull(){
        okHttpApi = null;
        RetrofitSingleton.sInstanse = null;
    }

    public OkHttpApi getOkHttpApi() {
        if (null == this.userProfile.getUserInfo()) {
            if (null == okHttpApi) {
                okHttpApi = new OkHttpApi(this);
            }
            return okHttpApi;
        }else {
            UserInfoBean userInfoBean = this.userProfile.getUserInfo();
            if (null != userInfoBean) {
                if (!TextUtils.isEmpty(userProfile.getUserInfo().getEmpId())) {
                    ProviderPool.getParamMap().put("userId", userProfile.getUserInfo().getEmpId());
                }
                if (!TextUtils.isEmpty(userProfile.getTokenLast())) {
                    ProviderPool.getParamMap().put("token", userProfile.getTokenLast());
                }
            }
            if (null == okHttpApi) {
                okHttpApi = new OkHttpApi(this);
            }
        }

        return okHttpApi;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
