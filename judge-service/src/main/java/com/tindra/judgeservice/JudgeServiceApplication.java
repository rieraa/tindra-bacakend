package com.tindra.judgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableDiscoveryClient
//@ComponentScan("com.tindra.config.exception")
@EnableFeignClients(basePackages = {"com.tindra.serviceclient.service"})
public class JudgeServiceApplication {

    public static void main(String[] args) {
        //
        InitRabbitMq.init();
        SpringApplication.run(JudgeServiceApplication.class, args);
    }

}
