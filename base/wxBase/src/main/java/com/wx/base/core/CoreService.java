package com.wx.base.core;

import com.wx.base.core.handRequest.Execute;
import com.wx.base.core.handRequest.HandStrategy;
import com.wx.base.error.WxError;
import com.wx.base.util.*;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 核心服务类
 */
@Component
public class CoreService {

    /**
     * 解析微信发送过来的内容,然后返回到微信公众号页面的内容
     *
     * @param request
     * @return
     */
    public String processRquest(HttpServletRequest request) {
        try {
            //获取request中的xml
            ServletInputStream inputStream = request.getInputStream();
            //解析xml
            Map<String, String> requestMap = ParseXml.parseXml(inputStream);

            return this.handRequest(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("系统出现错误,请联系管理员");
        }
    }

    private String handRequest(Map<String, String> requestMap) {

        String msgType = requestMap.get("MsgType");

        AssertUtils.notBlank(msgType, WxError.msg_type_is_null);

        HandStrategy bean = ApplicationUtil.getBean(msgType, HandStrategy.class);
        AssertUtils.notNull(bean,WxError.msg_type_can_found_strategy);

        Execute execute = new Execute(bean);
        return execute.execute(requestMap);

    }

}
