package com.tindra.judgeservice;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 初始化 RabbitMQ 连接和队列
 */
@Slf4j
public class InitRabbitMq {

    public static void init() {
        try {
            // 创建连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            // 设置 RabbitMQ 主机地址为本地主机
            factory.setHost("localhost");
            // 创建连接
            Connection connection = factory.newConnection();
            // 创建信道
            Channel channel = connection.createChannel();
            // 声明交换机名称
            String exchangeName = "exchange";
            // 声明交换机，类型为 direct
            channel.exchangeDeclare(exchangeName, "direct");

            // 声明队列名称
            String queueName = "judge_queue";
            // 声明队列，持久化、非排他、非自动删除
            channel.queueDeclare(queueName, true, false, false, null);
            // 将队列与交换机绑定，绑定键为 "judge"
            channel.queueBind(queueName, exchangeName, "judge");
            log.info("RabbitMQ 初始化成功");

        } catch (Exception e) {
            // 捕获异常并打印堆栈信息
            log.error("RabbitMQ 初始化失败", e);
        }
    }

    public static void main(String[] args) {

    }
}
