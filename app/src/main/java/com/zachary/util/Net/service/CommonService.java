package com.zachary.util.Net.service;


import com.zachary.util.Model.BaseRoot;
import com.zachary.util.Model.HttpResult;
import com.zachary.util.Model.UserInfoBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Zachary on 2016/8/4.
 */
public interface CommonService {

    /**
     * POST请求发送验证码
     *
     * @return
     */
    @POST("/smartSiteApp/sendVerifyCode")
    Observable<BaseRoot> getVerifyCode(@Body RequestBody body);

    /**
     * 登录
     * @param body
     * @return
     */
    @POST("/smartSiteApp/login")
    Observable<HttpResult<UserInfoBean>> login(@Body RequestBody body);


}
