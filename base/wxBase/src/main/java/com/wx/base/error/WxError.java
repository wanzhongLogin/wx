package com.wx.base.error;


public enum WxError implements Error{
    msg_type_is_null("0001","事件类型为空"),
    msg_type_can_found_strategy("0002","没有通过事件类型找到对应的处理器"),
    ;

    String errorCode;
    String errorMessage;
    private static final String ns = "";

    WxError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public WxError setErrorCode(String errorCode){
        this.errorCode = errorCode;
        return this;
    }
    public WxError setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
        return this;
    }
    @Override
    public String getNamespace() {
        return ns;
    }
    @Override
    public String getErrorCode() {
        return ns + "" + errorCode;
    }
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}