package com.tianma.springboot.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncService {

    //告诉spring这是一个异步方法
    @Async
    public void hello() {
        try {
            Thread.sleep(3000);
            log.info("----------处理积分----------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
