package com.wx.base.core.service.impl.key;


import com.BaseMessage;
import com.core.MessageUtil;
import com.model.Article;
import com.resMsg.NewsMessageRes;
import com.resMsg.TextMessageRes;
import com.wx.base.core.service.ReceiverMsg;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 点击事件
 * @author wan
 */
@Component("receiverMsgkey001")
public class ReceiverMsgkey001 extends ReceiverMsg {

    @Override
    public String parseRequest(BaseMessage bm) {



        TextMessageRes rs = new TextMessageRes();
        rs.setToUserName(bm.getToUserName());
        rs.setFromUserName(bm.getFromUserName());
        rs.setCreateTime(bm.getCreateTime());
        rs.setContent("hello");

        return MessageUtil.messageToXml(rs);

    }



}
