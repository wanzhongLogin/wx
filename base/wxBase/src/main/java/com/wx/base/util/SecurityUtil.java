package com.wx.base.util;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * 加密算法
 * @author wan
 */
public class SecurityUtil {

    //sha1加密
	public static final String SHA_1 = "SHA-1";

	//md5加密
	public static final String MD5 = "MD5";


    /**
     * 加密算法
     * sha-1
     * sha-256
     * md5
     */
	public String encode(String strSrc, String encodeType) {
		String result = null;
		try {
			if (StringUtils.isNotBlank(encodeType)) {
                //默认使用MD5
				encodeType = MD5;
			}
			MessageDigest md = MessageDigest.getInstance(encodeType);
			md.update(strSrc.getBytes("UTF-8"));
			result = bytes2Hex(md.digest());
		} catch (Exception e) {
			return strSrc;
		}
		return result;
	}

	public static String bytes2Hex(byte[] md) {
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		int j = md.length;
		char buf[] = new char[j*2];
		int k = 0;
		for(int i = 0;i<j;i++){
			byte b = md[i];
			buf[k++] = hexDigits[b >>> 4 & 0xf];
			buf[k++] = hexDigits[b & 0xf];
		}
		return new String(buf);
	}
}