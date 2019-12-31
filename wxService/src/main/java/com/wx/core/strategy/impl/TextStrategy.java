package com.wx.core.strategy.impl;

import com.BaseMessage;
import com.resMsg.TextMessageRes;
import com.wx.base.util.MessageUtil;
import com.wx.core.command.InvokerMsg;
import com.wx.core.command.CommandMsgDefault;
import com.wx.core.command.impl.ReceiverMsgTxt;
import com.wx.core.strategy.HandStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 文本类型的处理
 */
@Service("text")
public class TextStrategy implements HandStrategy {

    @Override
    public String execute(Map<String, String> requestMap, BaseMessage bm) {
        TextMessageRes tm = new TextMessageRes();
        tm.setFromUserName(bm.getFromUserName());
        tm.setToUserName(bm.getToUserName());
        tm.setCreateTime(bm.getCreateTime());
        tm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        tm.setContent("文本类型");
        String respXML = MessageUtil.messageToXml(tm);
        return respXML;
    }
}
