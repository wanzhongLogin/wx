package com.wx.core.strategy;


import com.BaseMessage;

import java.util.Map;

public interface HandStrategy {

    public String execute(Map<String, String> requestMap, BaseMessage bm);

}
