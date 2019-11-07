package com.wx.base.core.impl;


import com.BaseMessage;
import com.wx.base.util.MessageUtil;
import com.model.Article;
import com.resMsg.NewsMessageRes;
import com.wx.base.core.ReceiverMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信公众平台,发送图片消息
 */
public class ReceiverMsgImage extends ReceiverMsg {

    @Override
    public String parseRequest(BaseMessage bm) {
        Article article = new Article();
        article.setTitle("开源中国");
        article.setDescription("描述内容");
        article.setPicUrl("http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg");
        article.setUrl("http://m.oschina.net");

        List<Article> list = new ArrayList();
        list.add(article);

        NewsMessageRes news = new NewsMessageRes();

        news.setToUserName(bm.getToUserName());
        news.setFromUserName(bm.getFromUserName());
        news.setCreateTime(bm.getCreateTime());

        news.setArticles(list);
        news.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        news.setArticleCount(list.size());

        return MessageUtil.messageToXml(news);
    }

}
