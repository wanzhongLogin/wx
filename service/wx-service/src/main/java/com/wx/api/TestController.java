package com.wx.api;

import com.vo.UnionOpenIdVo;
import com.wx.base.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TokenUtil tokenUtil;

    @GetMapping("getOpenId")
    private void getOpenId(){
        UnionOpenIdVo user = tokenUtil.getOpenId("o9I_e0hjRHH0OLQZn0kcR1rWfBBc");

    }

}
