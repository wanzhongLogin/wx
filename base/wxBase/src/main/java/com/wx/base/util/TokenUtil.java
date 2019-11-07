package com.wx.base.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.util.Assert;

/**
 * token的util
 * 获取access_token
 * 有效期为2小时
 */
public class TokenUtil {

    static String appID = "wx81a506b53d13a7b3";
    static String appsecret = "51cc5daeb6569782350a5797e53a3e79";

    
    public static String getToken(){
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appID+"&secret="+appsecret;
        String str = HttpUtil.sendHttpsGET(tokenUrl);
        parseJson pj = JSON.parseObject(str, parseJson.class);
        String access_token = pj.getAccess_token();
        String expirse_in = pj.getExpires_in();

        Assert.notNull(access_token,"未获取到token");

        if(!expirse_in.equals("7200")){
            throw new RuntimeException("获取access_token失败,失败码:["+ expirse_in +"]");
        }
        return access_token;
    }

    public static void main(String args[]){
        String token = getToken();
        System.out.println(token);
    }
}

@Data
class parseJson{
    private String access_token;
    private String expires_in;
}