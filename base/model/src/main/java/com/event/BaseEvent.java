package com.event;

import lombok.Data;

/**
 * 事件基类
 * 注意:此消息只能是微信服务器推送过来
 *
 * @author wan
 */
@Data
public class BaseEvent {

    //开发者微信号(公众账号原始id)
    private String toUserName;

    //发送方账号(一个openId:可以在一个公众号中标识一个用户)
    private String fromUserName;

    //消息创建时间
    private long creaeTime;

    //消息类型(text:文本消息;image:图片消息;voice语音消息;video:视频消息;location:位置消息;link:链接消息)
    private String msgType;

    //消息id,64位整数
    private long msgId;

    //事件类型 subscribe(订阅)、unsubscribe(取消订阅)、click(自定义菜单点击事件)
    private String event;

}
