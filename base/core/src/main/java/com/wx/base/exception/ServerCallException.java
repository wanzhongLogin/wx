package com.wx.base.exception;

public class ServerCallException extends CheckedException {

    public ServerCallException(){
        super("服务调用异常");
    }

    public ServerCallException(String message){
        super(message);
    }

}
