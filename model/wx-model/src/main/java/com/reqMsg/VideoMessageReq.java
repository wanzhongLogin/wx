package com.reqMsg;


import com.BaseMessage;
import lombok.Data;

/**
 * 视频消息
 * @author wan
 */
@Data
public class VideoMessageReq extends BaseMessage {

	// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String MediaId;

	// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	private String ThumbMediaId;

}