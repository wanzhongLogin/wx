package com.wx.base.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext ac){
        applicationContext = ac;
    }

    public ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    public <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    public <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
