package com.tindra.questionservice.controller.inside;

import com.tindra.model.entity.Question;
import com.tindra.model.entity.QuestionSubmit;
import com.tindra.questionservice.service.QuestionService;
import com.tindra.questionservice.service.QuestionSubmitService;
import com.tindra.serviceclient.service.QuestionFeign;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 内部服务 仅内部调用
 */
@RestController
@RequestMapping("/private")
public class QuestionPrivateController implements QuestionFeign {


    @Resource
    QuestionService questionService;

    @Resource
    QuestionSubmitService questionSubmitService;
    /**
     * 获取对应题目信息
     * @param questionId
     * @return
     */
    @GetMapping("/get/id")
    @Override
    public Question getQuestionById(@RequestParam("questionId") Long questionId){
        return questionService.getById(questionId);
    }

    /**
     * 获取题目提交记录
     * @param questionSubmitId
     * @return
     */
    @GetMapping("/question_submit/get/id")
    @Override
    public QuestionSubmit getQuestionSubmitById (@RequestParam("questionSubmitId") Long questionSubmitId){
        return questionSubmitService.getById(questionSubmitId);
    }

    /**
     * 更新题目提交记录
     * @param questionSubmit
     * @return
     */
    @PostMapping("/question_submit/update")
    @Override
    public Boolean updateQuestionSubmitById (@RequestBody QuestionSubmit questionSubmit){
         return questionSubmitService.updateById(questionSubmit);
    }
}
