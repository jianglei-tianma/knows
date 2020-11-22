package com.tianma.springboot.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    /**
     * second(秒), minute(分), hour(时), day of month(日), month(月), and day of week(周几)
     */
    @Scheduled(cron = "0 * * * * SUN-SAT")
    public void helloWorld() {

        System.out.println("hello........");

    }
}
