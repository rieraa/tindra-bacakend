package com.tindra.judgeservice.judge.sandbox;


import com.tindra.model.sandbox.ExecuteCodeRequest;
import com.tindra.model.sandbox.ExecuteCodeResponse;

public interface SandBox {

    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
