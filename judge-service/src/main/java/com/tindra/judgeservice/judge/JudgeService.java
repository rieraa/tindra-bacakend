package com.tindra.judgeservice.judge;


import com.tindra.model.entity.QuestionSubmit;

public interface JudgeService {
    QuestionSubmit judgeCode(Long questionSubmitId);
}
