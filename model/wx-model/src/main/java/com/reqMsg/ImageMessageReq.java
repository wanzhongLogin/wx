package com.reqMsg;


import com.BaseMessage;
import lombok.Data;

/**
 * 图片消息
 * @author wan
 */
@Data
public class ImageMessageReq extends BaseMessage {

    //图片链接
    private String PicUrl;

}
