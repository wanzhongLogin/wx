package com.wx.base.util;

import org.springframework.context.ApplicationContext;

public class ApplicationUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext ac){
        applicationContext = ac;
    }

    public ApplicationContext getApplicationContext(){
        return applicationContext;
    }

}
