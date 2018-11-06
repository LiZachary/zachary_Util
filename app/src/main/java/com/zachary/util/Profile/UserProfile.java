package com.zachary.util.Profile;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.fiberhome.site.Action.ActionCode;
import com.fiberhome.site.Application.MyApplication;
import com.fiberhome.site.Model.UserInfoBean;
import com.fiberhome.site.Net.provider.ProviderPool;
import com.fiberhome.site.Util.ObjectSaveUtil;
import com.fiberhome.site.Util.PreferenceUtil;

/**
 * 类描述：用户信息属性控制类
 */
public class UserProfile {

    private final String KEY_MOBILE_LAST = "moblie_last";
    private final String KEY_GUID_LAST = "guid_last";
    private final String KEY_PROJECT_CODE_LAST = "project_code_last";
    private final String KEY_PROJECT_NAME_LAST = "project_name_last";
    private final String KEY_HEAD_IMAGE_LAST = "head_image_last";
    private final String KEY_PASSPORT = "user_passport";
    public final String KEY_TOKEN_LAST = "token_last";

    private static UserProfile instance;
    private Context context;

    public static UserProfile instance(Context context) {
        if (instance == null) {
            instance = new UserProfile(context);
        }

        return instance;
    }

    public UserProfile(Context context) {
        this.context = context;
    }


    /**
     * 在用户登录时，获取上次保存的token
     */
    public String getTokenLast() {
        return PreferenceUtil.getInstanse(context).getString(KEY_TOKEN_LAST, "");
    }

    /**
     * 在用户登录时，保存用户token；
     * 用户退出时，不要需要把token设置为空字符串
     *
     * @param token
     */
    public void setTokenLast(String token) {
        PreferenceUtil.getInstanse(context).putString(KEY_TOKEN_LAST, token);
    }

    /**
     * 在用户登录时，获取上次保存的手机号，自动填入
     */
    public String getMobileLast() {
        return PreferenceUtil.getInstanse(context).getString(KEY_MOBILE_LAST, "");
    }

    /**
     * 在用户登录时，保存用户手机号，下次用户登录时使用；
     * 用户退出时，不要需要把手机号设置为空字符串
     *
     * @param mobile
     */
    public void setMobileLast(String mobile) {
        PreferenceUtil.getInstanse(context).putString(KEY_MOBILE_LAST, mobile);
    }

    /**
     * 获取第一个工地的guid，作为默认的guid
     */
    public String getGuidLast() {
        return PreferenceUtil.getInstanse(context).getString(KEY_GUID_LAST, "");
    }

    /**
     * 在用户登录时，保存第一个guid的值；
     *
     * @param guid
     */
    public void setGuidLast(String guid) {
        PreferenceUtil.getInstanse(context).putString(KEY_GUID_LAST, guid);
    }

    /**
     * 获取第一个工地的guid对应的project的code的值；
     */
    public String getProjectCodeLast() {
        return PreferenceUtil.getInstanse(context).getString(KEY_PROJECT_CODE_LAST, "");
    }

    /**
     * 在用户登录时，保存第一个guid对应的project的code的值；
     *
     * @param projectCode
     */
    public void setProjectCodeLast(String projectCode) {
        PreferenceUtil.getInstanse(context).putString(KEY_PROJECT_CODE_LAST, projectCode);
    }

    /**
     * 获取第一个工地的guid对应的project的名字；
     */
    public String getProjectNameLast() {
        return PreferenceUtil.getInstanse(context).getString(KEY_PROJECT_NAME_LAST, "");
    }

    /**
     * 在用户登录时，保存第一个guid对应的project的名字；
     *
     * @param projectName
     */
    public void setProjectNameLast(String projectName) {
        PreferenceUtil.getInstanse(context).putString(KEY_PROJECT_NAME_LAST, projectName);
    }

    /**
     * 在用户登录时，获取图片值；
     */
    public String getHeadImageLast() {
        return PreferenceUtil.getInstanse(context).getString(KEY_HEAD_IMAGE_LAST, "");
    }

    /**
     * 在用户登录时，保存图片值；
     *
     * @param headImage
     */
    public void setHeadImageLast(String headImage) {
        PreferenceUtil.getInstanse(context).putString(KEY_HEAD_IMAGE_LAST, headImage);
    }

    /**
     * 在用户登录时，获取上次保存的用户名，自动填入
     */
    public String getUserNameLast() {
        return PreferenceUtil.getInstanse(context).getString(KEY_MOBILE_LAST, "");
    }

    /**
     * 在用户登录时，保存用户用户名，下次用户登录时使用；
     * 用户退出时，不要需要把手机号设置为空字符串
     *
     * @param userName
     */
    public void setUserNameLast(String userName) {
        PreferenceUtil.getInstanse(context).putString(KEY_MOBILE_LAST, userName);
    }

    /**
     * 在用户登录时，获取上次保存的密码，自动填入
     */
    public String getPassport() {
        return PreferenceUtil.getInstanse(context).getString(KEY_PASSPORT, "");
    }

    /**
     * 在用户登录时，保存密码
     *
     * @param passport
     */
    public void setPassport(String passport) {
        PreferenceUtil.getInstanse(context).putString(KEY_PASSPORT, passport);
    }

    public UserInfoBean getUserInfo() {
        return (UserInfoBean) ObjectSaveUtil.readObject(context.getApplicationContext());
    }

    //设置用户的信息，若userInfo为空，则SharePreference存储的数据删除
    public void setUserInfo(UserInfoBean userInfo) {
        MyApplication.setOkhttpNull();
        if (null != userInfo) {
            if (!TextUtils.isEmpty(userInfo.getEmpId())) {
                ProviderPool.getParamMap().put("userId", userInfo.getEmpId());
                //原来的代码并没有存储Token：setTokenLast("");
                ProviderPool.getParamMap().put("token", userInfo.getCustom01());
            }
            //保存userBean信息
            ObjectSaveUtil.saveObject(context.getApplicationContext(), userInfo);
        } else {
            ObjectSaveUtil.removeObject(context.getApplicationContext());
            setTokenLast("");
            ProviderPool.getParamMap().remove("userId");
            ProviderPool.getParamMap().remove("token");
            ProviderPool.getParamMap().remove("phone");
        }
        Intent intent = new Intent(
                ActionCode.INTENT_ACTION_LOGOUT);
        context.sendBroadcast(intent);
    }

    public void setUserBean(UserInfoBean userInfo) {
        MyApplication.setOkhttpNull();
        if (null != userInfo) {
//            if (!TextUtils.isEmpty(userInfo.getGuid())) {
//                ProviderPool.getParamMap().put("userId", userInfo.getGuid());
//            }
            //只保存userBean信息
            ObjectSaveUtil.saveObject(context.getApplicationContext(), userInfo);
        } else {
            ObjectSaveUtil.removeObject(context.getApplicationContext());
            setTokenLast("");
            ProviderPool.getParamMap().remove("userId");
            ProviderPool.getParamMap().remove("token");
            ProviderPool.getParamMap().remove("phone");
        }
        Intent intent = new Intent(
                ActionCode.INTENT_ACTION_LOGOUT);
        context.sendBroadcast(intent);
    }

    public String getUserId() {
        UserInfoBean userBean = getUserInfo();
        if (null != userBean) {
            return userBean.getEmpId();
        }
        return "";
    }

    public String getUserToken() {
        UserInfoBean userInfoBean = getUserInfo();
        if (null != userInfoBean) {
            return userInfoBean.getCustom01();
        }
        return "";
    }
}
