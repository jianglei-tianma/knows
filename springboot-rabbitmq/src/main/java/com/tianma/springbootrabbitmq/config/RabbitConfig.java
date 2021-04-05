package com.tianma.springbootrabbitmq.config;

import com.tianma.springbootrabbitmq.constant.RabbitMQConstant;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitTemplate:收发消息
 * AmqpAdmin:管理RabbitMQ的exchange，queue，binding....创建删除等
 *
 * @EnableRabbit：开启rabbit的功能 1、给RabbitMQ利用javaAPi创建exchange，queue，binding....
 * 1）、使用AmqpAdmin.declareXX方法来创建(exchange，queue，binding....)
 * amqpAdmin.declareExchange/Queue/Binding();
 * 2）、直接给容器中放exchange，queue，binding....，自动创建
 * <p>
 * 3）、消息发送，RabbitTemplate；
 * <p>
 * 新版的坑：我们必须当前功能与rabbitmq得建立连接，才能将容器中的组件创建过去
 * 2、如何监听消息队列里面的消息
 * @EnableRabbit
 * @RabbitListener(queues = "myqueue")
 */
@EnableRabbit
@Configuration
public class RabbitConfig {
    /**
     * 容器中放入自定义的messageConverter消息发送与接收就会用它进行转换
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    /**
     * SpringBoot会自动给RabbitMQ中创建这个交换机/队列/绑定关系
     * <p>
     * 1）、去RabbitMQ里面看有没有当前名字的交换机/队列/绑定关系，如果没有就创建，有就不管了。
     *
     * @return
     */
    @Bean(RabbitMQConstant.ORDER_EXCHANGE)
    public Exchange myExchange() {
        /**
         * String name,
         * boolean durable, boolean autoDelete, Map<String, Object> arguments
         */
        DirectExchange directExchange = new DirectExchange(RabbitMQConstant.ORDER_EXCHANGE, true, false, null);
        System.out.println("即将自动创建一个order-exchange");
        return directExchange;
    }

    @Bean(RabbitMQConstant.ORDER_QUEUE)
    public Queue myQueue() {
        Queue queue = new Queue(RabbitMQConstant.ORDER_QUEUE, true, false, false, null);
        System.out.println("即将自动创建一个order-queue");
        return queue;
    }

    @Bean(RabbitMQConstant.ORDER_BINDING)
    public Binding myBinding() {
        Binding binding = new Binding(RabbitMQConstant.ORDER_QUEUE,
                Binding.DestinationType.QUEUE, RabbitMQConstant.ORDER_EXCHANGE, "hello", null);
        System.out.println("即将自动创建一个order-binding");
        return binding;
    }

}
