package com.wx.feign;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 微信接入feign
 * @author wan
 */
@FeignClient(qualifier = "coreFeign",name = "wxService",fallback = CoreDegradedFeign.class)
public interface CoreFeign {

    @PostMapping("soa/coreApi/processRquest")
    public String processRquest(@RequestBody Map<String, String> map);
}
