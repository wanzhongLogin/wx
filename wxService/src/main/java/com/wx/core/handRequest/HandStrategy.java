package com.wx.core.handRequest;


import com.BaseMessage;

import java.util.Map;

public interface HandStrategy {

    public String execute(Map<String, String> requestMap, BaseMessage bm);

}
