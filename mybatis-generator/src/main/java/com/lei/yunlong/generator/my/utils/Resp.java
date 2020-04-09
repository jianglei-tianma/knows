package com.lei.yunlong.generator.my.utils;

import lombok.Data;

@Data
public class Resp<T> {

    //响应状态码
    private Integer code;

    //提示消息
    private String msg;

    //响应数据
    private T data;

    public Resp(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Resp() {
    }

    public Resp(T data) {
        this.data = data;
    }

    public static <T> Resp<T> ok(T data) {
        Resp<T> resp = new Resp<T>(data);
        resp.setCode(0);//操作成功
        resp.setMsg("success");
        return resp;
    }

    public static <T> Resp<T> fail(T data) {
        Resp<T> resp = new Resp<T>();
        resp.setCode(1);//操作失败
        resp.setMsg("fail");
        return resp;
    }

    public Resp<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public Resp<T> code(Integer code) {
        this.setCode(code);
        return this;
    }


}
