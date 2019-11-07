package com.reqMsg;


import com.BaseMessage;
import lombok.Data;

/**
 * 文本消息
 * @author wan
 */
@Data
public class TextMessageReq extends BaseMessage {

	//文本消息内容
	private String Content;

}
