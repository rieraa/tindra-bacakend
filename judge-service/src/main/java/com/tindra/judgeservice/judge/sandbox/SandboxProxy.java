package com.tindra.judgeservice.judge.sandbox;


import com.tindra.model.sandbox.ExecuteCodeRequest;
import com.tindra.model.sandbox.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SandboxProxy implements SandBox {

    private final SandBox codeSandbox;


    public SandboxProxy(SandBox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息：" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息：" + executeCodeResponse.toString());
        return executeCodeResponse;
    }


}
