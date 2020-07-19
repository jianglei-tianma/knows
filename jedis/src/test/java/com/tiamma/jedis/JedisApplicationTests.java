package com.tiamma.jedis;

import com.tiamma.jedis.utils.RedisTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;

@SpringBootTest
class JedisApplicationTests {
    @Autowired
    JedisPool jedisPool;
    @Autowired
    RedisTool redisTool;
    @Test
    void contextLoads() {
    }

    @Test
    public void test03() {
        Jedis jedis = jedisPool.getResource();

        String tocken = UUID.randomUUID().toString();

        boolean lock = RedisTool.tryGetDistributedLock(jedis, "jianglei:tokens:lock", tocken, 1);
        if (lock) {
            System.out.println("ok");
        }


    }

    @Test
    public void test02() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.128.130", 6379);

        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("mytest"));

        jedisPool.returnResource(jedis);

        jedisPool.close();

    }

    @Test
    public void test01() {
        Jedis jedis = new Jedis("192.168.128.130", 6379);

        jedis.set("mytest","123");
        String mytest = jedis.get("mytest");

        System.out.println(mytest);
        jedis.close();
    }



}
