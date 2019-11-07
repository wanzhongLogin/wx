package com.wx.base.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 接入微信基础配置
 * @author wan
 */
@Data
@Configuration
public class WeiXinConfig {


    @Value("${weixin.appId}")
    private String appId;

    @Value("${weixin.secret}")
    private String secret;

    @Value("${weixin.token}")
    private String token;

    @Value("${weixin.aesKey}")
    private String aesKey;

    @Value("${weixin.accessToken}")
    private String accessToken;

    @Value("${weixin.expiresIn}")
    private String expiresIn;

    @Value("${weixin.openId}")
    private String openId;

    @Bean
    public WeiXinConfig getWeiXinConfig(){
        return new WeiXinConfig();
    }
}
