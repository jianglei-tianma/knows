package com.tianma.springbootrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、导入starter-amqp
 * 2、一切自动配置好的，可以使用rabbittemplate发送消息给消息队列
 * 3、RabbitAutoConfiguration    RabbitProperties
 */
@SpringBootApplication
public class SpringbootRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqApplication.class, args);
    }

}
