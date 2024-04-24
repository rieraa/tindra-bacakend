package com.tindra.judgeservice.judge.sandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import com.tindra.common.BaseResponse;
import com.tindra.exception.BusinessException;
import com.tindra.judgeservice.judge.sandbox.SandBox;
import com.tindra.model.sandbox.ExecuteCodeRequest;
import com.tindra.model.sandbox.ExecuteCodeResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RemoteSandBox implements SandBox {


    // 代码沙箱的方式
    private String way = "native";

    // 代码沙箱的主机地址
    private String host = "localhost";

    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secret";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.add(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET);

        String url = "http://" + host + ":8989/execute/" + way;
        System.out.println("🚀 ~ file:RemoteSandBox.java method:executeCode line:30 -----url:" + url);
        ExecuteCodeRequest request = new ExecuteCodeRequest(); // 构造你的请求对象
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        System.out.println("Request Headers:");
        headers.forEach((key, value) -> System.out.println(key + ": " + value));

        BaseResponse response = JSONUtil.toBean(HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body(), BaseResponse.class);
        System.out.println("🚀 ~ file:RemoteSandBox.java method:executeCode line:27 -----responseStr:" + response);
        int code = response.getCode();
        if (code != 200) {
            throw new BusinessException(response.getCode(), response.getMessage());
        }

        Object object = response.getData();
        ExecuteCodeResponse executeCodeResponse = JSONUtil.toBean(JSONUtil.toJsonStr(object), ExecuteCodeResponse.class);
        return executeCodeResponse;

    }
}
