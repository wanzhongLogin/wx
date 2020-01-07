package com.reqMsg;


import com.BaseMessage;
import lombok.Data;

/**
 * 链接消息
 * @author wan
 */
@Data
public class LinkMessageReq extends BaseMessage {

    //消息标题
    private String title;
    //消息描述
    private String description;
    //消息链接
    private String url;
}
