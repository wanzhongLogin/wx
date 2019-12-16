package com.wx.core;

import com.wx.base.error.WxError;
import com.wx.base.util.ApplicationUtil;
import com.wx.base.util.AssertUtils;
import com.wx.base.util.ParseXml;
import com.wx.core.handRequest.Execute;
import com.wx.core.handRequest.HandStrategy;
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
     * @return
     */
    public String processRquest(Map<String, String> map) {
        try {
            return this.handRequest(map);
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
