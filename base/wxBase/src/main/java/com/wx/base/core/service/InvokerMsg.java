package com.wx.base.core.service;


import com.BaseMessage;

/**
 * 微信公众号命令执行者
 */
public class InvokerMsg<T> {
    private CommandMsg commandMsg;
    public InvokerMsg(CommandMsg commandMsg){
        this.commandMsg = commandMsg;
    }

    public T getRespXml(BaseMessage bm){
        return (T)commandMsg.execute(bm);
    }
}
