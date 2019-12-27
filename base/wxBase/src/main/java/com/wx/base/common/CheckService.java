package com.wx.base.common;

import com.wx.base.util.SecurityUtil;

import java.util.Arrays;

public class CheckService {

    /**
     * 检查
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    public static boolean check(String signature,String timestamp,String nonce,String echostr,String token){
        String[] arra = {token,timestamp,nonce};
        //对数组进行排序
        Arrays.sort(arra);
        StringBuffer sb = new StringBuffer();
        for(String s : arra) {
            sb.append(s);
        }
        //进行加密算法
        String encode = new SecurityUtil().encode(sb.toString(), "SHA1");
        return encode.equals(signature);
    }

}
