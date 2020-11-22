package com.tianma.java.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;

@RestController
@Slf4j
public class LambdaJavaTest {


    public static void test1() {
        //提供接口的匿名实现类的对象：旧的写法
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱天安门");
            }
        };
        r.run();
        //提供接口的匿名实现类的对象：新的写法
        Runnable r1 = () -> System.out.println("我爱天安门");
        r1.run();
    }

    public void test2(){
        //提供接口的匿名实现类的对象：旧的写法
        Comparator<Integer> com1 = new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }

        };

        int value = com1.compare(12, 21);
        System.out.println(value);

        //*****************************************
        //提供接口的匿名实现类的对象：新的写法--Lambda表达式
        Comparator<Integer> com2 = (o1,o2) -> Integer.compare(o1, o2);
        value = com2.compare(12, 2);
        System.out.println(value);

        //提供接口的匿名实现类的对象：新的写法--方法引用
        Comparator<Integer> com3 = Integer::compare;
        value = com3.compare(12, 2);
        System.out.println(value);
    }

    public static void main(String[] args) {

        test1();






    }

}


