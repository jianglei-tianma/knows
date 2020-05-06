package com.lei.knows.common;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 通用统一返回
 * @param <T>
 */
@ToString
@NoArgsConstructor
@Data
public class Response<T> {

    /*//响应数据
    private T data;
    //响应状态码
    private Integer code;
    //提示消息
    private String msg;

    public Response(T data) {
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        Response<T> resp = new Response<T>(data);
        resp.setCode(0);//操作成功
        resp.setMsg("success");
        return resp;
    }

    public static <T> Response<T> fail(String msg, Integer code) {
        Response<T> resp = new Response<T>();
        resp.setCode(1);//操作失败
        resp.setMsg(msg);
        return resp;
    }

    *//**
     * 如何设置链式调用，总是返回这个对象
     * @param code
     * @return
     *//*
    public Response<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    public Response<T> msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public Response<T> data(T code){
        this.setData(data);
        return this;
    }*/
}
