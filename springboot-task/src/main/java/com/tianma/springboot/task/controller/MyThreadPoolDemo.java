package com.tianma.springboot.task.controller;

import java.util.concurrent.*;

public class MyThreadPoolDemo {

    public static void main(String[] args) {

        //cpu密集
        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                9,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        try
        {
            try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            //模拟有10个顾客过来银行办理业务，目前池子里面有5个工作人员提供服务
            for (int i =1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

    public static void initPool() {
        //一个池子受理5个线程，类似一个银行有5个受理窗口
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一池1个工作线程，类似一个银行有1个受理窗口
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一池N个工作线程，类似一个银行有N个受理窗口
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try
        {
            /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            //模拟有10个顾客过来银行办理业务，目前池子里面有5个工作人员提供服务
            for (int i =1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
                //暂停秒
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
