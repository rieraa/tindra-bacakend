package com.tindra.judgeservice.judge.strategy;


import com.tindra.model.sandbox.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {

    /**
     * 对输出进行判断
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo judgeOutPut(JudgeContext judgeContext);
}
