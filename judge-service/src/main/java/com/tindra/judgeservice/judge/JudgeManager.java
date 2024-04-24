package com.tindra.judgeservice.judge;


import com.tindra.judgeservice.judge.strategy.DefaultJudgeStrategy;
import com.tindra.judgeservice.judge.strategy.JudgeContext;
import com.tindra.judgeservice.judge.strategy.JudgeStrategy;
import com.tindra.model.entity.QuestionSubmit;
import com.tindra.model.sandbox.JudgeInfo;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo judgeOutPut(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();

        return judgeStrategy.judgeOutPut(judgeContext);
    }

}
