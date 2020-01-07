package com.reqMsg;


import com.BaseMessage;
import lombok.Data;

/**
 * 地理位置消息
 * @author wan
 */
@Data
public class LocationMessageReq extends BaseMessage {

	// 地理位置纬度
	private String location_X;
	// 地理位置经度
	private String location_Y;
	// 地图缩放大小
	private String scale;
	// 地理位置信息
	private String label;
}
