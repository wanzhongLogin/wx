package com.wx.base.common;


/**
 * 全局常量
 */
public enum Globals {

    FromUserName("FromUserName","消息的来源id"),

    ;

    private String code;
    private String message;

    Globals(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
