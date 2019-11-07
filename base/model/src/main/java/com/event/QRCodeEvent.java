package com.event;

import lombok.Data;

/**
 * 扫描带参数二维码事件
 */
@Data
public class QRCodeEvent extends BaseEvent{

    //事件key值
    private String eventKey;

    //用于换取二维码图片
    private String ticket;

}
