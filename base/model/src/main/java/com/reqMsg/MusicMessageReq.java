package com.reqMsg;


import com.BaseMessage;
import lombok.Data;

/**
 * 音乐消息
 * @author wan
 */
@Data
public class MusicMessageReq extends BaseMessage {

	//标题
	private String title;
	//描述
	private String description;
	//音乐链接
	private String musicUrl;
	//高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String hQMusicUrl;
	//位0x0001被标志时，星标刚收到的消息。
	private String funcFlag;

}
