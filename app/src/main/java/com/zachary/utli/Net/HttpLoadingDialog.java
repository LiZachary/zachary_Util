package com.zachary.utli.Net;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.zachary.utli.R;

/**
 *
 * Created on 2018/7/8 15:40
 * @desc 网络请求时dialog
 * @auther Zachary
 */
public class HttpLoadingDialog extends Dialog {
    private String loadingText;
    private TextView loading_text;
    private int resLayout = R.layout.dialog_loading;
    private Context context;
    public HttpLoadingDialog(Context context) {
        super(context);
        this.context =context;
    }

    public HttpLoadingDialog(Context context, int theme) {
        super(context, theme);
        this.context =context;
    }
    public HttpLoadingDialog(Context context, int theme, int resid) {
        super(context, theme);
        this.context =context;
        this.loadingText = context.getResources().getString(resid);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(resLayout!=0){
            setContentView(resLayout);
            if(resLayout == R.layout.dialog_loading){
                loading_text = (TextView) findViewById(R.id.loading_text);
                loading_text.setText(loadingText);
            }
        }
    }

    public void setMessage(String message) {
        loadingText = message;
    }
    public void setMessage(int resid) {
        loadingText = context.getResources().getString(resid);
    }

    public int getResLayout() {
        return resLayout;
    }

    public void setResLayout(int resLayout) {
        this.resLayout = resLayout;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void cancel() {
        dismiss();
        ((Activity)context).finish();
        context = null;
        super.cancel();
    }
}
