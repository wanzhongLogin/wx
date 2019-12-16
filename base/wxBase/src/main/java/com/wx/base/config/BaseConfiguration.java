package com.wx.base.config;

import com.wx.base.exception.ExceptionHandle;
import com.wx.base.util.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 公共配置
 * @author wan
 */
@Configuration
@Component
@Import({Swagger2Configuration.class, ExceptionHandle.class})
public class BaseConfiguration {

    @Bean
    public ApplicationUtil getSpringContextUtils(@Autowired ApplicationContext applicationContext){
        ApplicationUtil springContextUtils = new ApplicationUtil();
        springContextUtils.setApplicationContext(applicationContext);
        return springContextUtils;
    }

}
