package com.wx.base.core.handRequest;

import com.BaseMessage;
import com.resMsg.TextMessageRes;
import com.wx.base.common.Globals;
import com.wx.base.core.InvokerMsg;
import com.wx.base.core.ReceiverMsg;
import com.wx.base.core.impl.CommandMsgDefault;
import com.wx.base.util.ApplicationUtil;
import com.wx.base.util.MessageUtil;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 事件推送类型
 */
@Service("event")
public class EventStrategy implements HandStrategy{

    @Override
    public String execute(Map<String, String> requestMap, BaseMessage bm) {

        String event = requestMap.get("Event");

        if (event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
            //订阅消息
            return MessageUtil.messageToXml(new TextMessageRes(bm,"该按钮没有配置相应的程序"));
        } else if (event.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
            //取消订阅
            return MessageUtil.messageToXml(new TextMessageRes(bm,"该按钮没有配置相应的程序"));
        } else if (event.equals(MessageUtil.EVENT_TYPE_CLICK)) {
            //自定义菜单

            //自定义click的值
            String eventKey = requestMap.get("EventKey");

            if (Globals.clickKey.contains(eventKey)) {
                ReceiverMsg rm = (ReceiverMsg) ApplicationUtil.getApplicationContext().getBean("receiverMsg" + eventKey);
                InvokerMsg<String> invoker = new InvokerMsg(new CommandMsgDefault<String>(rm));
                return invoker.getRespXml(bm);
            } else {
                return MessageUtil.messageToXml(new TextMessageRes(bm,"该按钮没有配置相应的程序"));
            }
        }else{
            return MessageUtil.messageToXml(new TextMessageRes(bm,"该按钮没有配置相应的程序"));
        }

    }

}
