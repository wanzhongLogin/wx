package com.resMsg;

import com.BaseMessage;
import com.annotation.XstreamCDATA;
import com.model.Music;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 音乐消息
 */
@Data
@XStreamAlias("xml")
public class MusicMessageRes extends BaseMessage {

    private Music Music;

    //消息类型(text:文本消息;image:图片消息;voice语音消息;video:视频消息;location:位置消息;link:链接消息)
    @XStreamAlias("MsgType")
    @XstreamCDATA
    protected String MsgType = "music";
}
