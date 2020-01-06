package com.wx.base.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class RestConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpRequestFactory.setReadTimeout(3000);
        httpRequestFactory.setConnectTimeout(3000);
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(httpRequestFactory));
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        //设置语言编码，解决中文乱码
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        messageConverters.forEach(httpMessageConverter -> {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                list.add(stringHttpMessageConverter);
            }else {
                list.add(httpMessageConverter);
            }
        });
        restTemplate.setMessageConverters(list);
        restTemplate.setInterceptors(Collections.singletonList(new LoggingRequestInterceptor()));

        return restTemplate;
    }

}
