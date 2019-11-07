package com.wx.base.core.impl;


import com.BaseMessage;
import com.wx.base.core.CommandMsg;
import com.wx.base.core.ReceiverMsg;

/**
 * 微信公众号,解析发送过来
 */
public class CommandMsgDefault<T> implements CommandMsg {

    private ReceiverMsg rm;

    public CommandMsgDefault(ReceiverMsg rm){
        this.rm = rm;
    }

    @Override
    public T execute(BaseMessage bm) {
        return (T) rm.parseRequest(bm);
    }

}