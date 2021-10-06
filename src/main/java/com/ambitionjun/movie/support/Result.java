package com.ambitionjun.movie.support;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code;
    private boolean success;
    private String msg;
    private Object data;

    public static Result success(Object data){
        return success(200,true,"操作成功",data);
    }
    public static Result success(int code,boolean success,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setSuccess(success);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    public static Result fail(boolean success,String msg,Object data){

       return fail(400,false,"操作失败",data);
    }
    public static Result fail(){

        return fail(400,false,"操作失败",null);
    }
    public static Result fail(int code,boolean success,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setSuccess(success);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
