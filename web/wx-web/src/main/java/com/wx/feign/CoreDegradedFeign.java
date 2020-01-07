package com.wx.feign;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CoreDegradedFeign implements CoreFeign{

    @Override
    public String processRquest(Map<String, String> map) {
        return null;
    }
}
