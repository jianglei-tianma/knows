package com.lagou.hdfs.hadoop.demo.controller;

import com.lagou.hdfs.hadoop.demo.service.LogCollectorTask;

import java.util.Timer;

public class LogController {

    /*    1、定时采集已滚动完毕日志文件
    2、将待采集文件上传到临时目录
    3、备份日志文件*/


    public static void main(String[] args) {

        Timer timer = new Timer();
        //定时采集任务调度
        //task：采集的业务逻辑
        //延迟时间
        //周期时间

        timer.schedule(new LogCollectorTask(),
                0,
                3600 * 1000);
    }


}
