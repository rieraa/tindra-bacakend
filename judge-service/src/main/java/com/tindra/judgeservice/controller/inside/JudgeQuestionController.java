package com.tindra.judgeservice.controller.inside;

import com.tindra.judgeservice.judge.JudgeService;
import com.tindra.model.entity.QuestionSubmit;
import com.tindra.serviceclient.service.JudgeFeign;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 内部服务 仅内部调用
 */
@RestController
@RequestMapping("/private")
public class JudgeQuestionController implements JudgeFeign {

    @Resource
    JudgeService judgeService;

    @PostMapping("/judgeCode")
    @Override
    public QuestionSubmit judgeCode(@RequestParam("questionSubmitId") Long questionSubmitId){
        return  judgeService.judgeCode(questionSubmitId);
    }
}
