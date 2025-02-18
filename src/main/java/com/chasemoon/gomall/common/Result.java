package com.chasemoon.gomall.common;

import lombok.Data;

@Data
public class Result <T>{
    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    public static <T> Result<T>success(T data){
        Result<T>result =new Result<>();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }
    public static <T> Result<T>error(){
        return null;
    }
}
