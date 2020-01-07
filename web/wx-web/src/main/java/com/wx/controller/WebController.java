package com.wx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("web")
@Controller
public class WebController {


    @GetMapping("test")
    public String test(){
        return "hello";
    }

}
