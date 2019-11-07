package com.wx.base.util;

import org.springframework.context.ApplicationContext;

public class ApplicationUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext ac){
        applicationContext = ac;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
