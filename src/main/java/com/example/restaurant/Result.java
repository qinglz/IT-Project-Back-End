package com.example.restaurant;

import java.util.HashMap;
import java.util.Map;

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

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}

