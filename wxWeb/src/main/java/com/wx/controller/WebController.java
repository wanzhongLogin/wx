package com.wx.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("web")
@Controller
public class WebController {

    @GetMapping(value = "/test")
    @ResponseBody
    public ModelAndView test(){
        ModelAndView mv = new ModelAndView("test");
        return mv;
    }

}
