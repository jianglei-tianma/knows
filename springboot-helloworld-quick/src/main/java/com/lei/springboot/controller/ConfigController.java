package com.lei.springboot.controller;

import com.lei.springboot.config.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ConfigController {

    @Value("${server.port:8080}")
    private String port;
    @Autowired
    private Environment env;
    @Autowired
    private User user;

    @GetMapping("/config")
    public Object config() {
        Map<Object, Object> result = new HashMap<>();
        result.put("port", port);

        result.put("profile", env.getProperty("spring.profiles.active"));
        result.put("user", user.getName());
        log.isDebugEnabled();
        return result;

    }
}
