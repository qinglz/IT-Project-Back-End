package com.example.restaurant;

import java.util.HashMap;
import java.util.Map;

/**
 * All APIs of this application will return a Result instance.
 * "code": is an attribute to show if API get an expected result.(What an expected result should be is shown in each API's comment.)
 * basically, code==1 means get an expected result. code==0 means get an unexpected result, missing important information, or other problem.
 * code==2 means the most important part of functionality is done, but some unimportant functionality failed.(E.g. created booking in
 * database successfully but the confirmation email cannot be sent)
 *  code==3 means missing login token.
 * "data": is an object that contain the data you want get from this API.
 *
 * "msg": is a sentence to describe what cause the API runs unexpectedly.
 *
 *
 *
 *
 * @param <T> data Type
 */
public class Result<T>{
    private Integer code;

    /** 信息返回*/
    private String msg;

    /** 信息返回数据*/
    private T data;

    /** 动态数据*/
    private Map map = new HashMap();

    public static <T> Result<T> success(T object) {
        Result<T> r = new Result<>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> Result<T> error(String msg) {
        Result r = new Result();
        r.msg = msg;
        r.code = 0;
        return r;
    }
    public static <T> Result<T> notLogin() {
        Result r = new Result();
        r.code = 3;
        return r;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public static <T> Result<T> partialError(T object, String msg) {
        Result r = new Result();
        r.msg = msg;
        r.data = object;
        r.code = 2;
        return r;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", map=" + map +
                '}';
    }
}

