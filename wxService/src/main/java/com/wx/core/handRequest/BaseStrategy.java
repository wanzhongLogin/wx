package com.wx.core.handRequest;

import com.wx.base.util.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseStrategy {


    @Autowired
    protected ApplicationUtil applicationUtil;

}
