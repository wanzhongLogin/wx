package com.wx.core.command.impl;


import com.BaseMessage;
import com.resMsg.TextMessageRes;
import com.wx.base.util.MessageUtil;
import com.wx.core.command.ReceiverMsg;
import org.springframework.stereotype.Component;


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
