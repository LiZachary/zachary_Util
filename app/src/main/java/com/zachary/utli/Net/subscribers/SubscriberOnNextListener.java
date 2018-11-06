package com.zachary.utli.Net.subscribers;

/**
 * Created on 2016/9/8 14:18
 *
 * @desc 订阅回调监听
 * @auther Zachary
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
    void onError(String msg);
}
