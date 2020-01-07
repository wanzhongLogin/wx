package com.wx;


import com.wx.base.config.BaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Import;

@SpringCloudApplication
@Import({BaseConfiguration.class})
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
