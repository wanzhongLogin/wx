package com.wx.core.strategy.impl;

import com.BaseMessage;
import com.resMsg.TextMessageRes;
import com.vo.UnionOpenIdVo;
import com.wx.base.common.Globals;
import com.wx.base.util.MessageUtil;
import com.wx.base.util.TokenUtil;
import com.wx.core.command.ReceiverMsg;
import com.wx.core.strategy.BaseStrategy;
import com.wx.core.strategy.HandStrategy;
import com.wx.entity.UserEntity;
import com.wx.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 事件推送类型
 */
@Service("event")
public class EventStrategy extends BaseStrategy implements HandStrategy {

    private static final Logger logger = LoggerFactory.getLogger(EventStrategy.class);


    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserRepository userRepository;


    @Override
    public String execute(Map<String, String> requestMap, BaseMessage bm) {
        String event = requestMap.get("Event");
        //todo 这个字符串需要全局变量
        String fromUserName = requestMap.get(Globals.FromUserName.getCode());

        //订阅消息
        if (event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
            this.saveUser(fromUserName);
            return MessageUtil.messageToXml(new TextMessageRes(bm,"感谢订阅,请点击xx,完善注册用户信息,可以得到更多优惠"));
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


    /**
     * 订阅的时候,保存用户的基本信息
     * @param fromUserName
     */
    private void saveUser(String fromUserName){
        UnionOpenIdVo wxVo = tokenUtil.getOpenId(fromUserName);
        String openid = wxVo.getOpenid();
        if(StringUtils.isNotBlank(openid)){
            UserEntity user = userRepository.getByOpenId(openid);
            if(user == null){
                user = new UserEntity();
                user.setOpenId(openid);
                user.setStatus(1);
            }else{
                //再次关注,直接已激活
                user.setStatus(2);
            }
            user.setSubscribe(wxVo.getSubscribe());
            user.setNickName(wxVo.getNickname());
            user.setSex(wxVo.getSex());
            user.setLanguage(wxVo.getLanguage());
            user.setCity(wxVo.getCity());
            user.setProvince(wxVo.getProvince());
            user.setCountry(wxVo.getCountry());
            user.setHeadimgUrl(wxVo.getHeadimgurl());
            user.setSubscribeTime(wxVo.getSubscribe_time());
            user.setSubscribeScene(wxVo.getSubscribe_scene());
            userRepository.save(user);
        }else{
            logger.info("未获取到用户信息");
        }
    }

}
