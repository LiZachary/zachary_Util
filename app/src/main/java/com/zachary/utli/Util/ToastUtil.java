package com.zachary.utli.Util;

import android.widget.Toast;
import com.zachary.utli.Application.MyApplication;

/**
 * Created by Zachary on 2017-02-27.
 * 自定义Toast
 */
public class ToastUtil {

    private static Toast toast;

    //Short
    public static void showToastShort(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(),
                    message,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    //Long
    public static void  showToastLong(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(),
                    message,
                    Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}
