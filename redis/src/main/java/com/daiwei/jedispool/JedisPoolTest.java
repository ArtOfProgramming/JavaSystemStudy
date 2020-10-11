package com.daiwei.jedispool;

import com.daiwei.jedispool.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolTest {

    public static void main(String[] args) {
        JedisPool jedisPool = JedisPoolUtils.getJedisPoolInstance();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("k2", "hello");
            System.out.println(jedis.get("k2"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
