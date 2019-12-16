package com.wx.base.exception;

public class ServerCallException extends BizBusinessException {

    public ServerCallException(){
        super("服务调用异常");
    }

    public ServerCallException(String message){
        super(message);
    }

}
