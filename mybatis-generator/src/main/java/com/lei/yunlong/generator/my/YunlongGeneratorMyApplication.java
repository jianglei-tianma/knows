package com.lei.yunlong.generator.my;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.lei.yunlong.generator.my.dao")
@SpringBootApplication
public class YunlongGeneratorMyApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunlongGeneratorMyApplication.class, args);
    }

}
