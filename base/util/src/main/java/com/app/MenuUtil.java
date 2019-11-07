package com.app;


import com.alibaba.fastjson.JSONObject;
import com.button.*;
import com.core.HttpUtil;

/**
 * 创建微信公众号菜单
 * @author wan
 */
public class MenuUtil {
    static String accessToken = TokenUtil.getToken();
    static String appID = "wx81a506b53d13a7b3";
    static String appsecret = "51cc5daeb6569782350a5797e53a3e79";

    public static void creMenu(){
        //菜单
        Menu menu = new Menu();

        //点击按钮
        ClickButton cb1 = new ClickButton();
        cb1.setName("key000");
        cb1.setType("click");
        cb1.setKey("key000");

        //url按钮
        ViewButton vb1 = new ViewButton();
        vb1.setName("qq.com");
        vb1.setType("view");
        vb1.setUrl("http://www.qq.com/");

        //点击按钮
        ClickButton cb3 = new ClickButton();
        cb3.setName("key001");
        cb3.setType("click");
        cb3.setKey("key001");

        //点击按钮
        ClickButton cb4 = new ClickButton();
        cb4.setName("key002");
        cb4.setType("click");
        cb4.setKey("key002");

        //按钮群
        ComplexButton com1 = new ComplexButton();
        com1.setName("菜单1");

        //按钮加入菜单群
        com1.setSub_button(new BaseButton[]{cb1});

        //按钮群
        ComplexButton com2 = new ComplexButton();
        com2.setName("菜单2");

        //按钮加入菜单群
        com2.setSub_button(new BaseButton[]{cb3, cb4});

        //放入菜单列表
        menu.setButton(new BaseButton[]{com1, com2, vb1});

        //转换成json
        String s = JSONObject.toJSON(menu).toString();

        //创建菜单
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;

        //发送请求
        String s1 = HttpUtil.sendHttpsPOST(url, s);

        System.out.println(s1);
    }

    public static void delMenu(){
        //删除菜单
        String deleteUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete*access_token=" + accessToken;
        //发送请求
        String s1 = HttpUtil.sendHttpsGET(deleteUrl);
    }

    public static void getMenu(){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+accessToken;
        String s = HttpUtil.sendHttpsGET(getUrl);
        System.out.println(s);
    }

    public static void main(String args[]){
        MenuUtil.creMenu();
    }
}