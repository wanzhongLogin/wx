package com;

import com.annotation.XstreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 请求消息基类(普通用户>公众账号)
 * @author wan
 */
@Data
public class BaseMessage {

    //开发者微信号(公众账号原始id)
    @XStreamAlias("ToUserName")
    @XstreamCDATA
    public String ToUserName;

    //发送方账号(一个openId:可以在一个公众号中标识一个用户)
    @XStreamAlias("FromUserName")
    @XstreamCDATA
    public String FromUserName;

    //消息创建时间
    @XStreamAlias("CreateTime")
    public Long CreateTime;

}
