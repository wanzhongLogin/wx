package com.wx.core.strategy.impl;

import com.BaseMessage;
import com.resMsg.TextMessageRes;
import com.wx.base.common.Globals;
import com.wx.base.util.MessageUtil;
import com.wx.core.command.ReceiverMsg;
import com.wx.core.strategy.BaseStrategy;
import com.wx.core.strategy.HandStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 事件推送类型
 */
@Service("event")
public class EventStrategy extends BaseStrategy implements HandStrategy {

    private static final Logger logger = LoggerFactory.getLogger(EventStrategy.class);


    @Override
    public String execute(Map<String, String> requestMap, BaseMessage bm) {
        String event = requestMap.get("Event");
        //todo 这个字符串需要全局变量
        String fromUserName = requestMap.get(Globals.FromUserName.getCode());

        if (event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
            logger.info("订阅事件");
            //订阅消息
            return MessageUtil.messageToXml(new TextMessageRes(bm,"感谢订阅"));
        } else if (event.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
            //取消订阅
            return MessageUtil.messageToXml(new TextMessageRes(bm,"该按钮没有配置相应的程序"));
        } else if (event.equals(MessageUtil.EVENT_TYPE_CLICK)) {
            //自定义click的值
            String eventKey = requestMap.get("EventKey");
            ReceiverMsg rm = (ReceiverMsg) applicationUtil.getApplicationContext().getBean("receiverMsg" + eventKey);
            if(rm == null){
                return MessageUtil.messageToXml(new TextMessageRes(bm,"该按钮没有配置相应的程序"));
            }
            String xml = rm.parseRequest(bm);
            return xml;
        }else{
            return MessageUtil.messageToXml(new TextMessageRes(bm,"该按钮没有配置相应的程序"));
        }

    }

}
