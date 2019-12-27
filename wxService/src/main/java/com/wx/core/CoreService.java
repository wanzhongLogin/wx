package com.wx.core;

import com.wx.base.common.Globals;
import com.wx.base.util.ApplicationUtil;
import com.wx.base.util.AssertUtils;
import com.wx.core.handRequest.Execute;
import com.wx.core.handRequest.HandStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 核心服务类
 */
@Component
public class CoreService {

    @Autowired
    private ApplicationUtil applicationUtil;

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

        AssertUtils.notBlank(msgType, Globals.msg_type_is_null.getMessage());

        HandStrategy bean = applicationUtil.getBean(msgType, HandStrategy.class);
        AssertUtils.notNull(bean,Globals.msg_type_can_found_strategy.getMessage());

        Execute execute = new Execute(bean);
        return execute.execute(requestMap);

    }

}
