package com.wx.base.core.service.impl;


import com.BaseMessage;
import com.core.MessageUtil;
import com.resMsg.TextMessageRes;
import com.wx.base.core.service.ReceiverMsg;

/**
 * 消息类接收者
 */
public class ReceiverMsgTxt extends ReceiverMsg {

    private String content;

    public ReceiverMsgTxt(){}
    public ReceiverMsgTxt(String content){
        this.content = content;
    }

    @Override
    public String parseRequest(BaseMessage bm) {
        TextMessageRes tm = new TextMessageRes();
        tm.setFromUserName(bm.getFromUserName());
        tm.setToUserName(bm.getToUserName());
        tm.setCreateTime(bm.getCreateTime());
        tm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

        tm.setContent(content);

        String respXML = MessageUtil.messageToXml(tm);
        return respXML;
    }
}
