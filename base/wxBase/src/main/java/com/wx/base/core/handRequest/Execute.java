package com.wx.base.core.handRequest;


import com.BaseMessage;

import java.util.Map;

public class Execute {

    private HandStrategy handStrategy;

    public Execute(){

    }

    public Execute(HandStrategy handStrategy){
        this.handStrategy = handStrategy;
    }

    public String execute(Map<String, String> requestMap){

        BaseMessage bm = new BaseMessage();
        bm.setToUserName(requestMap.get("FromUserName"));
        bm.setFromUserName(requestMap.get("ToUserName"));
        bm.setCreateTime(System.currentTimeMillis());

        return handStrategy.execute(requestMap,bm);
    }


}
