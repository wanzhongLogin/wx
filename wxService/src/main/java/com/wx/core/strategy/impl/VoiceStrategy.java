package com.wx.core.strategy.impl;

import com.BaseMessage;
import com.resMsg.TextMessageRes;
import com.resMsg.VoiceMessageRes;
import com.wx.base.util.MessageUtil;
import com.wx.core.strategy.HandStrategy;

import java.util.Map;


/**
 * 语音处理
 */
public class VoiceStrategy implements HandStrategy {
    @Override
    public String execute(Map<String, String> requestMap, BaseMessage bm) {
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
