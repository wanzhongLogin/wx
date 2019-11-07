package com.wx.base.core.handRequest;

import com.BaseMessage;
import com.wx.base.core.InvokerMsg;
import com.wx.base.core.impl.CommandMsgDefault;
import com.wx.base.core.impl.ReceiverMsgImage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("image")
public class ImageStrategy implements HandStrategy {
    @Override
    public String execute(Map<String, String> requestMap, BaseMessage bm) {
        //图片消息
        InvokerMsg<String> invoker = new InvokerMsg(new CommandMsgDefault<String>(new ReceiverMsgImage()));
        return invoker.getRespXml(bm);
    }
}
