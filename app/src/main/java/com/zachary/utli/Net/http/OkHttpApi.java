package com.zachary.utli.Net.http;

import android.text.TextUtils;

import com.zachary.utli.Application.MyApplication;
import com.zachary.utli.Model.HttpResult;
import com.zachary.utli.Net.RetrofitSingleton;
import com.zachary.utli.Net.exception.ApiException;
import com.zachary.utli.Net.service.CommonService;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class OkHttpApi {
    private MyApplication mApplication;
    private Retrofit retrofit;
    private CommonService commonService;


    public OkHttpApi(MyApplication mApplication) {
        this.mApplication = mApplication;
        //获得Retrofit的单例，其中包括了拦截器/请求头内容
        retrofit = RetrofitSingleton.getInstanse(mApplication.getApplicationContext());
        commonService = retrofit.create(CommonService.class);
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCode() != 1) {
                if (httpResult.getCode() == 2) {//需重新登录
//                    mApp.userProfile.setUserInfo((UserInfo) null);
//                    mApp.userProfile.reLogin();
                }
                if (!TextUtils.isEmpty(httpResult.getMsg())) {
                    throw new ApiException(httpResult.getMsg());
                } else {
                    throw new ApiException("加载失败");
                }
            }
            return httpResult.getData();
        }
    }

}

