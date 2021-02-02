package com.lagou.hdfs.client.demo.singlton;

import com.lagou.hdfs.client.demo.service.LogCollectorTask;

import java.io.IOException;
import java.util.Properties;

public class PropTool2 {

    //类加载时初始化执行一次即可
    //使用静态代码块实现 懒汉
    //volatile关键字式java中禁止指定重排顺序的关键字，保证有序性和可见性
    //在多线程的情况，单线程不可见
    private static volatile Properties prop = null;

    //出现线程安全问题
    public static Properties getProp() throws IOException {

        if (prop == null) {
            synchronized ("lock") {
                if (prop == null) {
                    prop = new Properties();
                    prop.load(LogCollectorTask.class.getClassLoader().getResourceAsStream("application.properties"));

                }
            }
        }
        return prop;
    }



}
