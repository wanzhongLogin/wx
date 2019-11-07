package com.wx.eureka;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {

    public static void main(String args[]){
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(EurekaServer.class);
        springApplicationBuilder.web(true).run(args);
    }

}
