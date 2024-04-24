package com.tindra.judgeservice.rabbit;

import com.rabbitmq.client.Channel;
import com.tindra.judgeservice.judge.JudgeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
@Slf4j
public class Consumer {

    @Resource
    JudgeService judgeService;

    @SneakyThrows
    @RabbitListener(queues = "judge_queue", ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("Received <" + message + ">");
        Long questionSubmissionId = Long.parseLong(message);

        try {
            judgeService.judgeCode(questionSubmissionId);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("Error when judging code", e);
            channel.basicNack(tag, false, true);
        }
    }

}
