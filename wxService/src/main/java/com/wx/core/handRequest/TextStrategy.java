package com.wx.core.handRequest;

import com.BaseMessage;
import com.wx.core.InvokerMsg;
import com.wx.core.impl.CommandMsgDefault;
import com.wx.core.impl.ReceiverMsgTxt;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 文本类型的处理
 */
@Service("text")
public class TextStrategy implements HandStrategy {


    @Override
    public String execute(Map<String, String> requestMap, BaseMessage bm) {

        //文本类型
        String content = "文本类型";

        InvokerMsg<String> invoker = new InvokerMsg(new CommandMsgDefault<String>(new ReceiverMsgTxt(content)));
        return invoker.getRespXml(bm);
    }
}
