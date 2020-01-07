package com.reqMsg;


import com.BaseMessage;
import lombok.Data;

/**
 * 语音消息
 * @author wan
 */
@Data
public class VoiceMessageReq extends BaseMessage {

	// 消息媒体id，可以调用多媒体文件下载接口拉取该媒体
	private String MediaId;

	//语音格式：amr
	private String Format;

	// 语音识别结果，UTF8编码
	private String Recognition;

}
