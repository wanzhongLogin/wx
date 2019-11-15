package com.wx.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("webController")
@Controller
public class WebController {

    @GetMapping(value = "/hello")
    public ModelAndView test(){
        ModelAndView mv = new ModelAndView("hello");
        return mv;
    }

}
