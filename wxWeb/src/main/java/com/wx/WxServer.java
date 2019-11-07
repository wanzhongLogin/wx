package com.wx;

import com.core.ApplicationUtil;
import com.wx.base.config.Swagger2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;


@SpringCloudApplication
@EnableFeignClients
@Import({ApplicationUtil.class,Swagger2Config.class})
public class WxServer {

    public static void main(String args[]) {
        ConfigurableApplicationContext run = SpringApplication.run(WxServer.class, args);
        ApplicationUtil.setApplicationContext(run);
    }

}
