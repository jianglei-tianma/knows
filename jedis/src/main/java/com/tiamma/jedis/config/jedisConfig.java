package com.tiamma.jedis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class jedisConfig {

    @Bean
    public JedisPool jedisPool(){
        JedisPool pool = new JedisPool("192.168.128.130",6379);

        return pool;
    }
}
