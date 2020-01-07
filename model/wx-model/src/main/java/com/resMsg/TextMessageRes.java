package com.resMsg;

import com.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;


/**
 * 文本消息
 * @author wan
 */
@XStreamAlias(value = "xml")
@Data
public class TextMessageRes extends BaseMessage{

	//文本消息内容
	@XStreamAlias("Content")
	private String Content;


    //消息类型(text:文本消息;image:图片消息;voice语音消息;video:视频消息;location:位置消息;link:链接消息)
    @XStreamAlias("MsgType")
    private String MsgType = "text";


    public TextMessageRes(){

    }

    public TextMessageRes(String FromUserName,String ToUserName,Long CreateTime){
        super.ToUserName = ToUserName;
        super.FromUserName = FromUserName;
        super.CreateTime = CreateTime;
    }

    public TextMessageRes(BaseMessage bm,String content){
        super.ToUserName = bm.getToUserName();
        super.FromUserName = bm.getFromUserName();
        super.CreateTime = bm.getCreateTime();
        this.Content = content;
    }

    public TextMessageRes setContent(String Content){
        this.Content = Content;
        return this;
    }

}
