/*
 * Copyright (C) 2015 House365. All rights reserved.
 */

package com.zachary.utli.Net.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：Http请求公共参数数据存储类
 * 创建人：FanLei
 * 创建时间：2016/1/14 9:18
 * 修改人：Zachary
 */
public final class ProviderPool {
    
    private static Map<String, String> paramMap = new HashMap<>();

    public static Map<String, String> getParamMap() {
        return paramMap;
    }

    public static void setParamMap(Map<String, String> paramMap) {
        ProviderPool.paramMap = paramMap;
    }

    public static void setClient(String client) {
        paramMap.put("client", client);
    }

    public static void setToken(String token) {
        paramMap.put("token", token);
    }

    public static void setApi_key(String api_key) {
        paramMap.put("api_key", api_key);
    }

    public static void setDeviceId(String deviceId) {
        paramMap.put("deviceId", deviceId);
    }

    public static void setPhone(String phone) {
        paramMap.put("phone", phone);
    }

    public static void setUserId(String userId) {
        paramMap.put("userId", userId);
    }

    public static void setApp_channel(String app_channel) {
        paramMap.put("app_channel", app_channel);
    }

    public static String getApp_channel() {
        return paramMap.get("app_channel");
    }

    public static void setCity(String city) {
        paramMap.put("workerlist", city);
    }

    public static void setVersion(String version) {
        paramMap.put("version", version);
    }

    public static void setIs_virtual(String is_virtual) {
        paramMap.put("is_virtual", is_virtual);
    }
}
