package com.wx;

import com.wx.base.util.ApplicationUtil;
import com.wx.config.Swagger2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;


//@SpringCloudApplication
@SpringBootApplication
@EnableFeignClients
@Import({ApplicationUtil.class, Swagger2Config.class})
public class WxServer {

    public static void main(String args[]) {
        ConfigurableApplicationContext run = SpringApplication.run(WxServer.class, args);
        ApplicationUtil.setApplicationContext(run);
    }

}
