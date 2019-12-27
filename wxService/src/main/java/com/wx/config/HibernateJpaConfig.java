package com.wx.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.wx.repository"})
@EntityScan(basePackages = "com.wx.entity")
@EnableTransactionManagement
public class HibernateJpaConfig {

}
