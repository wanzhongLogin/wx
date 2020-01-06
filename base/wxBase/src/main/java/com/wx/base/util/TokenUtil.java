package com.wx.base.util;

import com.alibaba.fastjson.JSONObject;
import com.vo.UnionOpenIdVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * token的util
 * 获取access_token
 * 有效期为2小时
 */
@Component
public class TokenUtil {

    @Value("${weixin.appId}")
    private String appID;

    @Value("${weixin.secret}")
    private String appsecret;

    @Value("${weixin.urls.get_access_token}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weixin.urls.get_open_id}")
    private String urlOpen;

    /**
     * 获取access_token
     * @return
     */
    public String getAccessToken(){
        String body = restTemplate.getForObject(url, String.class, appID, appsecret);
        if(StringUtils.isNotBlank(body)){
            JSONObject jsonObject = JSONObject.parseObject(body);
            String access_token = jsonObject.getString("access_token");
            String expires_in = jsonObject.getString("expires_in");
            Assert.notNull(access_token,"未获取到token");
            if(!expires_in.equals("7200")){
                throw new RuntimeException("获取access_token失败,失败码:["+ expires_in +"]");
            }
            return access_token;
        }else{
            return null;
        }
    }

    /**
     * 根据openId,获取用户信息
     * @param openId
     * @return
     */
    public UnionOpenIdVo getOpenId(String openId){
        String accessToken = this.getAccessToken();
        UnionOpenIdVo body = restTemplate.getForObject(urlOpen, UnionOpenIdVo.class, accessToken, openId);
        return body;
    }
}
