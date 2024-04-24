package com.tindra.judgeservice.judge.sandbox.impl;



import com.tindra.judgeservice.judge.sandbox.SandBox;
import com.tindra.model.enums.JudgeInfoEnum;
import com.tindra.model.enums.QuestionSubmitStatusEnum;
import com.tindra.model.sandbox.ExecuteCodeRequest;
import com.tindra.model.sandbox.ExecuteCodeResponse;
import com.tindra.model.sandbox.JudgeInfo;

import java.util.List;


public class TestSandBox implements SandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        List<String> input = executeCodeRequest.getInput();
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(2000L);
        judgeInfo.setTime(1000L);
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutput(input);
        executeCodeResponse.setMessage("系统运行正常");
        executeCodeResponse.setJudgeInfo(judgeInfo);
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.COMPLETED.getCode());
        return executeCodeResponse;

    }
}
