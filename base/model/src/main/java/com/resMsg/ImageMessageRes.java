package com.resMsg;


import com.BaseMessage;
import com.model.Image;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 图片消息
 * @author wan
 */
@Data
public class ImageMessageRes extends BaseMessage {

	//图片链接
	private Image Image;

    //消息类型(text:文本消息;image:图片消息;voice语音消息;video:视频消息;location:位置消息;link:链接消息)
    @XStreamAlias("MsgType")
    protected String MsgType = "image";

}
