package com.zachary.util.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class PreferenceUtil {

    private static SharedPreferences mPrefs;

    private static PreferenceUtil preferenceUtil;

    public static PreferenceUtil getInstanse(Context context) {
        if (null == preferenceUtil) {
            initPreferenceUtil(context);
        }

        return preferenceUtil;
    }

    private static synchronized void initPreferenceUtil(Context context) {
        preferenceUtil = new PreferenceUtil();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void clean(String key) {
        mPrefs.edit().remove(key).commit();
    }

    public <T> void addListItem(String key, T t) {
        JsonArray array = this.getList(key);
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(t, TypeToken.get(t.getClass()).getType());
        array.add(parser.parse(jsonStr).getAsJsonObject());
        mPrefs.edit().putString(key, array.toString()).commit();
    }



    public String[] getStringArray(String key) {
        String str = mPrefs.getString(key, (String) null);
        if (str != null) {
            String[] temparr = str.split(",");
            return temparr;
        } else {
            return null;
        }
    }

    public boolean isHasString(String key, String val) {
        String[] temparray = this.getStringArray(key);
        if (temparray != null) {
            for (int i = 0; i < temparray.length; ++i) {
                if (val.equals(temparray[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isIndexOfString(String key, String val) {
        String splitag = ",";
        String str = mPrefs.getString(key, (String) null);
        if (str != null) {
            str = splitag + str;
            if (str.indexOf(splitag + val) > -1) {
                return true;
            }
        }

        return false;
    }

    public JsonArray getList(String key) {
        String oldInfo = mPrefs.getString(key, (String) null);
        JsonArray oldInfoArray;
        if (oldInfo == null) {
            oldInfoArray = new JsonArray();
        } else {
            try {
                JsonParser e = new JsonParser();
                oldInfoArray = e.parse(oldInfo).getAsJsonArray();
            } catch (Exception var5) {
                var5.printStackTrace();
                oldInfoArray = new JsonArray();
            }
        }

        return oldInfoArray;
    }


    public void putBoolean(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).commit();
    }

    public void putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).commit();
    }

    public void putString(String key, String value) {
        mPrefs.edit().putString(key, value).commit();
    }

    public void putFloat(String key, float value) {
        mPrefs.edit().putFloat(key, value).commit();
    }

    public void putLong(String key, long value) {
        mPrefs.edit().putLong(key, value).commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPrefs.getBoolean(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return mPrefs.getFloat(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mPrefs.getLong(key, defValue);
    }


}
