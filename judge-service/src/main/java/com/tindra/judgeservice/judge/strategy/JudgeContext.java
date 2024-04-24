package com.tindra.judgeservice.judge.strategy;


import com.tindra.model.dto.jundge.JudgeCase;
import com.tindra.model.entity.Question;
import com.tindra.model.entity.QuestionSubmit;
import com.tindra.model.sandbox.JudgeInfo;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
