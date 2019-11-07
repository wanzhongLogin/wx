package com.resMsg;


import com.BaseMessage;
import com.annotation.XstreamCDATA;
import com.model.Article;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 文本响应消息
 * @author wan
 */
@Data
public class NewsMessageRes extends BaseMessage {

    //图文消息个数,限制10个以内
    private int ArticleCount;

    //多条图文消息,默认第一个为大图
    private List<Article> Articles;

    //消息类型(text:文本消息;image:图片消息;voice语音消息;video:视频消息;location:位置消息;link:链接消息)
    @XStreamAlias("MsgType")
    @XstreamCDATA
    private String MsgType = "news";

}
