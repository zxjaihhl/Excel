package com.zxjiahhl.excel.utils;

import lombok.Getter;

/**
 * @Auther: 周明军
 * @Date: 2018/9/20 09:35
 * @Description: 响应状态
 */
@Getter
public enum ResultStatus {
    OK(200, "成功"),
    ERROR(201,"无返回数据"),
    BAD_REQUEST(400, "错误请求"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "未找到结果"),
    TIMEOUT(408, "超时"),
    SERVER_ERROR(500, "服务器错误"),

    PARAM_EMPTY(600, "参数为空"),
    PARAM_INSUFFICIENT(601, "参数不全"),
    INVALID_JSON(602, "JSON格式错误"),
    PARAM_ERROR(603, "参数错误"),
    NO_SERVICE(700, "未找到该服务"),
    LOGIN_ERROR(701, "用户名或者密码错误"),
    ;

    private Integer code;
    private String message;

    ResultStatus(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}