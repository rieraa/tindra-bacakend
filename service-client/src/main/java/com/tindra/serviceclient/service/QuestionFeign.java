package com.tindra.serviceclient.service;


import com.tindra.model.entity.Question;
import com.tindra.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "question-service", path = "/api/question/private")
public interface QuestionFeign {


    /**
     * 获取对应题目信息
     * @param questionId
     * @return
     */
    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") Long questionId);

    /**
     * 获取题目提交记录
     * @param questionSubmitId
     * @return
     */
    @GetMapping("/question_submit/get/id")
    QuestionSubmit getQuestionSubmitById (@RequestParam("questionSubmitId") Long questionSubmitId);

    /**
     * 更新题目提交记录
     * @param questionSubmit
     * @return
     */
    @PostMapping("/question_submit/update")
    Boolean updateQuestionSubmitById (@RequestBody QuestionSubmit questionSubmit);


}
