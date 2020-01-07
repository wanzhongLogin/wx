package com.wx.api;


import com.wx.core.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 提供微信公众号连接后台
 */
@RequestMapping("soa/coreApi")
@RestController
public class CoreApiController {

    @Autowired
    private CoreService coreService;

    @PostMapping("processRquest")
    public String processRquest(@RequestBody Map<String, String> map){
        return coreService.processRquest(map);
    }

}
