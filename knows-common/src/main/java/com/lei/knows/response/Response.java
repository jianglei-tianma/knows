package com.lei.knows.response;


import lombok.*;

/**
 * 通用统一返回
 * @param <T>
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    //响应状态码
    private Integer code;

    //提示消息
    private String msg;

    //响应数据
    private T data;

    public Response(T data) {
        this.data = data;
    }

    public static<T> Response<T> ok(T data){
        Response<T> resp = new Response<T>(data);
        resp.setCode(0);//操作成功
        resp.setMsg("success");
        return resp;
    }

    public static<T> Response<T> fail(T data){
        Response<T> resp = new Response<T>();
        resp.setCode(1);//操作失败
        resp.setMsg("fail");
        return resp;
    }

    public Response<T> msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public Response<T> code(Integer code){
        this.setCode(code);
        return this;
    }


}
