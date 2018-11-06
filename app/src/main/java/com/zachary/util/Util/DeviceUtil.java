package com.zachary.util.Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Zachary on 2018-05-02.
 */

public class DeviceUtil {
    public DeviceUtil() {
    }

    public static boolean isNetConnect(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager)context.getApplicationContext().getSystemService("connectivity");
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected() && info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return false;
    }

    public static boolean isWifiConnect(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getApplicationContext().getSystemService("connectivity");
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null && activeNetInfo.getType() == 1;
    }

    public static boolean isOpenLoaction(Context context) {
        try {
            LocationManager lm = (LocationManager)context.getApplicationContext().getSystemService("location");
            boolean GPS_status = lm.isProviderEnabled("gps");
            boolean NETWORK_status = lm.isProviderEnabled("network");
            if (GPS_status || NETWORK_status) {
                return true;
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return false;
    }

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager)context.getApplicationContext().getSystemService("phone");
        String id = telephonyManager.getDeviceId();
        if (TextUtils.isEmpty(id)) {
            id = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (TextUtils.isEmpty(id)) {
                id = "";
            } else {
                id = "androidid" + id;
            }
        }

        return id;
    }
}
