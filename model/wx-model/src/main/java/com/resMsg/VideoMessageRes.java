package com.resMsg;

import com.BaseMessage;
import com.model.Video;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 视频相应消息
 */
@Data
public class VideoMessageRes extends BaseMessage {

    private Video Video;

    //消息类型(text:文本消息;image:图片消息;voice语音消息;video:视频消息;location:位置消息;link:链接消息)
    @XStreamAlias("MsgType")
    protected String MsgType = "video";

}
