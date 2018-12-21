package com.zxjiahhl.excel.utils;

import lombok.Data;

/**
 * @Auther: 周明军
 * @Date: 2018/9/20 09:12
 * @Description: 响应体
 */
@Data
public class R {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 具体内容
     */
    private Object result;


    public R(){
        code = ResultStatus.OK.getCode();
        message = ResultStatus.OK.getMessage();
    }

    public static R ok(Object result){
        R r = new R();
        r.result = result;
        return r;
    }

    public static R ok() {
        return new R();
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.code = code;
        r.message = msg;
        return r;
    }



}