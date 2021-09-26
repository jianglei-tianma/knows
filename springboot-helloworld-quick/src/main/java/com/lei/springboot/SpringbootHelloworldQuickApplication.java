package com.lei.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Conditional;


@SpringBootApplication
public class SpringbootHelloworldQuickApplication {

    public static void main(String[] args) {
        // 1、返回我们的IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootHelloworldQuickApplication.class, args);

        // 2、查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {

            System.out.println(name);
        }



    }

}
