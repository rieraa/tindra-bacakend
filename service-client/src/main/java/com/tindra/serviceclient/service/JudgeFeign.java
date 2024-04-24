package com.tindra.serviceclient.service;


import com.tindra.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "judge-service", path = "/api/judge/private")
public interface JudgeFeign {
    @PostMapping("/judgeCode")
    QuestionSubmit judgeCode(@RequestParam("questionSubmitId") Long questionSubmitId);
}
