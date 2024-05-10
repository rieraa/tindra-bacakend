package com.tindra.judgeservice.rabbit;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.tindra.exception.BusinessException;
import com.tindra.judgeservice.judge.JudgeService;
import com.tindra.model.entity.QuestionSubmit;
import com.tindra.model.enums.QuestionSubmitStatusEnum;
import com.tindra.model.sandbox.JudgeInfo;
import com.tindra.serviceclient.service.QuestionFeign;
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
    @Resource
    private QuestionFeign questionFeign;
    QuestionSubmit questionSubmit = new QuestionSubmit();

    @SneakyThrows
    @RabbitListener(queues = "judge_queue", ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("Received <" + message + ">");
        Long questionSubmissionId = Long.parseLong(message);
        questionSubmit.setId(questionSubmissionId);

        try {
            judgeService.judgeCode(questionSubmissionId);
            channel.basicAck(tag, false);
        } catch (BusinessException e) {
            log.error("Error when judging code", e);
            // 处理评测失败的结果
            handleFailedJudgment(e);
            channel.basicNack(tag, false, false); // 不要求重新放入队列
        }
    }

    // 处理评测失败的结果
    private void handleFailedJudgment(BusinessException e) {
        // 在这里实现对评测失败的结果进行处理，可以记录日志或发送通知等操作
        System.out.println("🚀 ~ file:Consumer.java method:handleFailedJudgment line:40 -----e:" + e);
        // 可以在这里添加其他处理逻辑
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(e.getMessage());
        String  judgeInfoStr = JSONUtil.toJsonStr(judgeInfo);
        questionSubmit.setJudgeInfo(judgeInfoStr);
        questionSubmit.setStatus(QuestionSubmitStatusEnum.FAIL.getCode());
        questionFeign.updateQuestionSubmitById(questionSubmit);

    }

}
