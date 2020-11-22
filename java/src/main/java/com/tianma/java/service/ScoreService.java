package com.tianma.java.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScoreService {

    @Async
    public void addScore() {
        try{

            Thread.sleep(5 * 1000);
            log.info("-------------------处理积分-------------------");

        } catch (Exception e) {
            log.info("增加积分异常[{}]", e);
        }
    }
}
