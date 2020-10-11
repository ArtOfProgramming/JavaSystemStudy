package com.daiwei.basic;

/**
 * redis内容：
 * 1.用途：缓存
 * 2.配置
 * 3.主从复制
 * 4.支持数据类型
 * 5.常用操作
 * 6.RedisTemplate 会做序列化处理
 * 7.事务 (a.运行时错误指令，不会影响事务中其他正常指令，b.错误指令，连坐)
 * 8.池化
 * 9.待补充 spring集成，springboot集成，集成哨兵
 */

import java.util.Set;
import java.util.UUID;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class RedisTest {

    private final static Jedis jedis = new Jedis("192.168.173.130", 6379);

    /**
     * 基本测试
     */
    public static void basicTest() {
        System.out.println(jedis.ping());
        jedis.set("k1", "hello1");
        jedis.set("k2", "hello2");
        jedis.set("k3", "hello3");
        System.out.println(jedis.get("k2"));
        Set<String> set = jedis.keys("*");
        for (String s : set) {
            System.out.println(jedis.get(s));
        }
    }

    public static void transactionTest() {
        // 初始化两个账户
        jedis.set("user1balance", "100");
        jedis.set("user2balance", "0");
        // 错误制造
        jedis.set("error", "e");
        System.out.println("user1balance:" + jedis.get("user1balance"));
        System.out.println("user2balance:" + jedis.get("user2balance"));
        // 测试正常流程,事务中，无加塞
        jedis.watch("user1balance");
        jedis.watch("user2balance");
        // user1转给user2 10块钱
        if (Integer.valueOf(jedis.get("user1balance")) < 10) {
            jedis.unwatch();
            System.out.println("user1余额不足");
            return;
        } else {
            Transaction transaction = jedis.multi();
            transaction.incr("error");
            transaction.getSet(UUID.randomUUID().toString(), "error2");
            transaction.decrBy("user1balance", 10);
            transaction.incrBy("user2balance", 10);
            transaction.exec();
            // 回滚
//            transaction.discard();
        }
        System.out.println("user1balance:" + jedis.get("user1balance"));
        System.out.println("user2balance:" + jedis.get("user2balance"));
    }

    public static void main(String[] args) {
//        basicTest();
        transactionTest();
    }
}
