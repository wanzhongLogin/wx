package com.wx.controller;


import com.wx.base.core.CoreService;
import com.wx.base.common.CheckService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 微信接入接口
 * @author wan
 */
@RestController
@RequestMapping("coreController")
public class CoreController {

    @Autowired
    private CheckService checkService;

    @Autowired
    private CoreService coreService;

    /**
     * 与公众号连接入口
     * @param req
     * @param res
     * @throws Exception
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");

        // 微信加密签名
        String signature = StringUtils.trimToEmpty(req.getParameter("signature"));

        // 时间戳
        String timestamp = StringUtils.trimToEmpty(req.getParameter("timestamp"));

        // 随机数
        String nonce = StringUtils.trimToEmpty(req.getParameter("nonce"));

        // 随机字符串
        String echostr = StringUtils.trimToEmpty(req.getParameter("echostr"));

        //检验
        boolean check = checkService.check(signature, timestamp, nonce, echostr);

        if(!check){
            return echostr;
        }else{
            return "非法请求";
        }
    }

    /**
     * 与微信服务器交互
     * 文本,图片,地理位置,音乐...
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String change(HttpServletRequest req,HttpServletResponse res) throws Exception {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        //处理消息
        String respXml = coreService.processRquest(req);
        return respXml;
    }


}
