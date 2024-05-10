package com.tindra.judgeservice.judge.sandbox.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.tindra.common.BaseResponse;
import com.tindra.common.BusinessCode;
import com.tindra.common.ResultUtils;
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
        try {
            String ACCESS_KEY = "pub";
            String SECRET_KEY = "access";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String url = "http://" + host + ":8989/execute/" + way;
            System.out.println("🚀 ~ file:RemoteSandBox.java method:executeCode line:30 -----url:" + url);
            ExecuteCodeRequest request = new ExecuteCodeRequest(); // 构造你的请求对象
            String json = JSONUtil.toJsonStr(executeCodeRequest);

            HttpResponse response = HttpUtil.createPost(url)
                    .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                    .body(json)
                    .execute();
            

            // 解析响应
            BaseResponse baseResponse = JSONUtil.toBean(response.body(), BaseResponse.class);
            int code = baseResponse.getCode();
            if (code != 200) {
                throw new BusinessException(code, baseResponse.getMessage());
            }

            Object object = baseResponse.getData();
            ExecuteCodeResponse executeCodeResponse = JSONUtil.toBean(JSONUtil.toJsonStr(object), ExecuteCodeResponse.class);
            return executeCodeResponse;
        } catch (Exception e) {
            System.out.println("🚀 ~ file:e method:executeCode line:59 -----e:" + e);
            // 在捕获异常后，你可以选择抛出自定义的业务异常或者返回一个包含错误信息的对象
            throw new BusinessException(BusinessCode.REMOTE_SANDBOX_ERROR.getCode(), e.getMessage());
        }
    }

}
