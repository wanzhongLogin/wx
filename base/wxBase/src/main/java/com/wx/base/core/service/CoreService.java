package com.wx.base.core.service;

import com.BaseMessage;
import com.core.ApplicationUtil;
import com.core.MessageUtil;
import com.core.ParseXml;
import com.resMsg.TextMessageRes;
import com.wx.base.core.service.impl.CommandMsgDefault;
import com.wx.base.core.service.impl.ReceiverMsgImage;
import com.wx.base.core.service.impl.ReceiverMsgTxt;
import com.wx.base.core.service.impl.ReceiverMsgVoice;
import com.wx.base.common.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 核心服务类
 */
@Component
public class CoreService {

    @Autowired
    private ApplicationUtil applicationUtil;

    public String processRquest(HttpServletRequest request){

        //解析微信发送过来的内容,然后返回到微信公众号页面的内容
        String respXml = null;

        try {
            //获取request中的xml
            ServletInputStream inputStream = request.getInputStream();
            //解析xml
            Map<String, String> requestMap = ParseXml.parseXml(inputStream);


            String fromUserName = requestMap.get("FromUserName");
            String toUserName = requestMap.get("ToUserName");
            String msgType = requestMap.get("MsgType");

            BaseMessage bm = new BaseMessage();
            bm.setToUserName(fromUserName);
            bm.setFromUserName(toUserName);
            bm.setCreateTime(System.currentTimeMillis());

            //事件推送
            if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){

                //事件类型
                String event = requestMap.get("Event");

                //订阅消息
                if(event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
                    //todo 发送还原订阅的信息
                }else if(event.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){//取消订阅

                }else if(event.equals(MessageUtil.EVENT_TYPE_CLICK)){//自定义菜单

                    //自定义click的值
                    String eventKey = requestMap.get("EventKey");

                    if(Globals.clickKey.contains(eventKey)){
                        ReceiverMsg rm = (ReceiverMsg) applicationUtil.getApplicationContext().getBean("receiverMsg"+eventKey);
                        InvokerMsg<String> invoker = new InvokerMsg(new CommandMsgDefault<String>(rm));
                        respXml = invoker.getRespXml(bm);
                    }else{
                        respXml = createErrorMessage(bm,"该按钮没有配置相应的程序");
                    }
                }
            }
            //文本类型
            else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
                String content = "文本类型";
                InvokerMsg<String> invoker = new InvokerMsg(new CommandMsgDefault<String>(new ReceiverMsgTxt(content)));
                respXml = invoker.getRespXml(bm);
            }
            //图片消息
            else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
                InvokerMsg<String> invoker = new InvokerMsg(new CommandMsgDefault<String>(new ReceiverMsgImage()));
                respXml = invoker.getRespXml(bm);
            }
            //语音消息
            else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
                InvokerMsg<String> invoker = new InvokerMsg(new CommandMsgDefault<String>(new ReceiverMsgVoice(requestMap)));
                return invoker.getRespXml(bm);
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("系统出现错误,请联系管理员");
        }
        return respXml;
    }

    public static String createErrorMessage(BaseMessage bm, String content){
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
