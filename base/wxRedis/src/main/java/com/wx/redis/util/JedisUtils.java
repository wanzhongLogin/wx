package com.wx.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.function.Function;

/**
 * jedis 工具
 *
 * @author su
 * @date 2018-09-22
 */
public class JedisUtils {


    @Autowired
    private ShardedJedisPool shardedJedisPool;

    /**
     * 是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        return doRedisOpt(j -> j.exists(key));
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        return doRedisOpt(j -> j.set(key, value));
    }

    /**
     * @param key
     * @param value
     * @param timeout seconds
     * @return
     */
    public Long set(String key, String value, int timeout) {
        return doRedisOpt(j -> {
            j.set(key, value);
            return j.expire(key, timeout);
        });
    }

    /**
     * @param key
     * @return
     */
    public String get(String key) {
        return doRedisOpt(j -> j.get(key));
    }

    /**
     * @param key
     * @return
     */
    public Long del(String key) {
        return doRedisOpt(j -> j.del(key));
    }

    private <T> T doRedisOpt(Function<ShardedJedis, T> opts) {
        try (ShardedJedis jedis = shardedJedisPool.getResource()) {
            return opts.apply(jedis);
        }
    }

}
