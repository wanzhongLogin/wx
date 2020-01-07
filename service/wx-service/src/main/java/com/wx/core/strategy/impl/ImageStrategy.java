package com.wx.core.strategy.impl;

import com.BaseMessage;
import com.model.Article;
import com.resMsg.NewsMessageRes;
import com.wx.base.util.MessageUtil;
import com.wx.core.strategy.HandStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 图片处理类型
 */
@Service("image")
public class ImageStrategy implements HandStrategy {
    @Override
    public String execute(Map<String, String> requestMap, BaseMessage bm) {
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
