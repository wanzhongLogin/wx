package com.resMsg;

import com.BaseMessage;
import com.model.Voice;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 语音消息
 * @author wan
 */
@Data
public class VoiceMessageRes extends BaseMessage {

    //语音
    private Voice Voice;

    //消息类型(text:文本消息;image:图片消息;voice语音消息;video:视频消息;location:位置消息;link:链接消息)
    @XStreamAlias("MsgType")
    protected String MsgType = "voice";

}
