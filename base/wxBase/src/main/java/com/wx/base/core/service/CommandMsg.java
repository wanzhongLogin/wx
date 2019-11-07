package com.wx.base.core.service;


import com.BaseMessage;

/**
 * 接收微信公众号消息命令
 */
public interface CommandMsg<T> {

    public T execute(BaseMessage bm);

}
