package com.wx.core.impl.key;


import com.BaseMessage;
import com.model.Article;
import com.resMsg.NewsMessageRes;
import com.wx.base.util.MessageUtil;
import com.wx.core.ReceiverMsg;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * wan000按钮事件
 * 文本响应
 * 此处设计可以变成详细的数据库配置类型
 */
@Component("receiverMsgkey000")
public class ReceiverMsgkey000 extends ReceiverMsg {

    @Override
    public String parseRequest(BaseMessage bm) {

        Article article = new Article();
        article.setTitle("a");
        article.setDescription("df");
        article.setPicUrl("http://img4.imgtn.bdimg.com/it/u=3806557979,3233516071&fm=214&gp=0.jpg");
        article.setUrl("http://m.oschina.net");

        List<Article> list = new ArrayList();
        list.add(article);

        NewsMessageRes news = new NewsMessageRes();

        news.setToUserName(bm.getToUserName());
        news.setFromUserName(bm.getFromUserName());
        news.setCreateTime(bm.getCreateTime());

        news.setArticles(list);
        news.setArticleCount(list.size());
        return MessageUtil.messageToXml(news);
    }

}
