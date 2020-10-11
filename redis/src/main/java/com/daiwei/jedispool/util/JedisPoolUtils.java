package com.daiwei.jedispool.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * JedisPool工具类
 */
public class JedisPoolUtils {

    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtils() {

    }

    public static JedisPool getJedisPoolInstance() {
        if (null == jedisPool) {
            synchronized (JedisPoolUtils.class) {
                if (null == jedisPool) {
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxTotal(1000);
                    config.setMaxIdle(32);
                    config.setMaxWaitMillis(120 * 1000 * 1000);
                    config.setTestOnBorrow(true);
                    jedisPool = new JedisPool(config, "127.0.0.1", 6379, 1000, "parking");
                }
            }
        }
        return jedisPool;
    }
}
