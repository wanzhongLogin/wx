package com.wx.base.core.handRequest;

import com.BaseMessage;
import com.wx.base.core.InvokerMsg;
import com.wx.base.core.impl.CommandMsgDefault;
import com.wx.base.core.impl.ReceiverMsgVoice;

import java.util.Map;

public class VoiceStrategy implements HandStrategy{
    @Override
    public String execute(Map<String, String> requestMap, BaseMessage bm) {
        //语音消息
        InvokerMsg<String> invoker = new InvokerMsg(new CommandMsgDefault<String>(new ReceiverMsgVoice(requestMap)));
        return invoker.getRespXml(bm);
    }
}
