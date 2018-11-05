package com.zachary.utli.Net.util;

import android.os.Build;
import android.text.TextUtils;

import com.zachary.utli.Net.provider.ProviderPool;

/**
 * description：网络请求头信息
 * Eg：CUSTOM_ID=;ACCESS_TOKEN=;SOURCE_TYPE=Android;DEVICE_ID=81D6973E-489C-4D4F-AD5E-4B0F63167711;VERSION=1.4.0;APP_NAME=zhgz
 * created by Zachary
 * Time:  2018-07-03  15:49
 */

public class HeadUtils {
    static String android_id = "35" +
            Build.BOARD.length()%10+ Build.BRAND.length()%10 +

            Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 +

            Build.DISPLAY.length()%10 + Build.HOST.length()%10 +

            Build.ID.length()%10 + Build.MANUFACTURER.length()%10 +

            Build.MODEL.length()%10 + Build.PRODUCT.length()%10 +

            Build.TAGS.length()%10 + Build.TYPE.length()%10 +

            Build.USER.length()%10 ; //13 位
    public static String getHeader(){
        StringBuilder sb = new StringBuilder();
        //暂时写成固定数据
        sb.append("CUSTOM_ID=");
        sb.append(TextUtils.isEmpty(ProviderPool.getParamMap().get("userId")) ? "" : ProviderPool.getParamMap().get("userId"));
        sb.append(";");
        sb.append("LOGIN_TOKEN=");
        sb.append(TextUtils.isEmpty(ProviderPool.getParamMap().get("token")) ? "" : ProviderPool.getParamMap().get("token"));
        sb.append(";");
        sb.append("SOURCE_TYPE=Android;");
        sb.append("DEVICE_ID=");
        sb.append(android_id);//ProviderPool.getParamMap().get("deviceId")
        sb.append(";");
        sb.append("VERSION=");
        sb.append(ProviderPool.getParamMap().get("version"));
        sb.append(";");
        sb.append("APP_NAME=zhgd;");
        return sb.toString();
    }
}  