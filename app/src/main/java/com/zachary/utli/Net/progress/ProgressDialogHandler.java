package com.zachary.utli.Net.progress;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.zachary.utli.Net.HttpLoadingDialog;
import com.zachary.utli.R;


/**
 *
 * Created on 2016/9/8 14:09
 * @desc 网络请求时的dialog处理
 * @auther Aric
 */
public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private HttpLoadingDialog loadingDialog;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;
    private int resid;//-1时，不显示dialog
    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable,int resid) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
        this.resid = resid;
    }

    private void initProgressDialog(){
        if (loadingDialog == null && resid!=-1) {
            loadingDialog = new HttpLoadingDialog(context, R.style.dialog,resid);

            loadingDialog.setCancelable(cancelable);

            if (cancelable) {
                loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        }
    }

    private void dismissProgressDialog(){
        if (loadingDialog != null && resid!=-1) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

}
