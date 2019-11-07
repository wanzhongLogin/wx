package com.core;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

public class ApplicationUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext ac){
        applicationContext = ac;
    }

    public ApplicationContext getApplicationContext(){
        return applicationContext;
    }

}
