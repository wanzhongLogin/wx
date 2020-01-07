package com.wx.base.exception;

/**
 * rest返回结果code枚举
 */
public enum RestResponseCodeEnum {
    SERVICE_ERROR(-20, "服务异常"),
    DATA_ERROR(-60, "数据异常"),
    FIELD_VALIDTION_ERROR(-80, "数据校验异常"),
    UNKNOW_ERROR(-1000, "未知错误"),
    BUSINESS_ERROR(-10, "业务异常"),
    ;


    private Integer code;

    private String msg;

    RestResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
