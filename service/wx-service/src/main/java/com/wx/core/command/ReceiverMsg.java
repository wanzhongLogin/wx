package com.wx.core.command;


import com.BaseMessage;

/**
 * 微信公众号命令的具体接收者
 * @Autowired
 */
public abstract class ReceiverMsg{

    /**
     * 解析微信公众号平台发送过来的消息报文
     */
    public abstract String parseRequest(BaseMessage bm);

}
