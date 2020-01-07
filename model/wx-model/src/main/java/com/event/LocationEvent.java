package com.event;

import lombok.Data;

/**
 * 上报地理位置事件
 * @author wan
 */
@Data
public class LocationEvent extends BaseEvent{

    //地理位置经度
    private String latitude;

    //地理位置纬度
    private String longitude;

    //地理位置精度
    private String precision;

}
