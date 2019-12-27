package com.wx.base.common;


/**
 * 全局常量
 */
public enum Globals {

    FromUserName("1","消息的来源id"),
    msg_type_is_null("2","msg_type为空"),
    msg_type_can_found_strategy("3","msg_type未找到处理改类型的程序")
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
