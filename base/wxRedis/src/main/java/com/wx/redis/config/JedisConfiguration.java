package com.wx.redis.config;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wx.redis.util.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.*;

import java.util.concurrent.TimeUnit;

/**
 * @author wan
 */
@Configuration
@Slf4j
public class JedisConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.password}")
    private String password;


    @Bean(destroyMethod = "destroy")
    ShardedJedisPool jedisPool(){
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMinIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestWhileIdle(true);
        config.setTestOnBorrow(true);
        config.setMinEvictableIdleTimeMillis(TimeUnit.MINUTES.toMillis(10));
        JedisShardInfo info = new JedisShardInfo(host,port,timeout);
        info.setPassword(Strings.emptyToNull(password));
        ShardedJedisPool pool = new ShardedJedisPool(config, Lists.newArrayList(info));
        try(ShardedJedis jedis = pool.getResource()){
            jedis.get("test-connection");
        }
        return pool;
    }

    @Bean(destroyMethod = "destroy")
    public JedisPool jedisPool2(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        return jedisPool;
    }


    @Bean(name="redisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        template.setConnectionFactory(factory);
        //key序列化方式
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(redisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(redisSerializer);
        //key haspmap序列化
        template.setHashKeySerializer(redisSerializer);
        return template;
    }

    @Bean(name ="jedisUtils")
    public JedisUtils jedisUtils(){
        return new JedisUtils();
    }
}
