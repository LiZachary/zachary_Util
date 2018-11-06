package com.zachary.util.Model;
/**
 *
 * Created on 2018-08-28
 * @desc 接口返回封装
 * @auther Zachary
 */
public class HttpResult<T> {
    /**
     *  返回code
     */
    private int code;
    /**
     *  错误状态下提示信息
     */
    private String msg;
    /**
     *  实体数据
     */
    private T data;
    private int result;
    private int success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
