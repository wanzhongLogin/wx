package com.wx.base.core.impl;


import com.BaseMessage;
import com.wx.base.util.MessageUtil;
import com.resMsg.TextMessageRes;
import com.resMsg.VoiceMessageRes;
import com.wx.base.core.ReceiverMsg;

import java.util.Map;

/**
 * 微信公众号:语音响应
 */
public class ReceiverMsgVoice extends ReceiverMsg {

    private Map<String, String> requestMap;

    public ReceiverMsgVoice(){}
    public ReceiverMsgVoice(Map<String, String> requestMap){
        this.requestMap = requestMap;
    }

    @Override
    public String parseRequest(BaseMessage bm) {
        VoiceMessageRes voice = new VoiceMessageRes();

        //语音消息文件的标志
        String mediaId = requestMap.get("MediaId");
        //语音格式amr
        String format = requestMap.get("Format");
        //语音识别结果
        String recognition = requestMap.get("Recognition");

        TextMessageRes tm = new TextMessageRes();
        tm.setFromUserName(bm.getFromUserName());
        tm.setToUserName(bm.getToUserName());
        tm.setCreateTime(bm.getCreateTime());
        tm.setContent("语音识别结果:["+recognition+"]");

        return MessageUtil.messageToXml(tm);
    }
}
