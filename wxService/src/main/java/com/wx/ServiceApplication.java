package com.wx;


import com.wx.base.config.BaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.wx.feign"})
@Import({BaseConfiguration.class})
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
