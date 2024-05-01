package com.tindra.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {

    // 响应码
    private int code;

    // 响应数据
    private T data;

    // 错误消息/成功消息
    private String message;

    /**
     * 构造函数
     * @param code
     * @param data
     * @param message
     */
    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(BusinessCode businessCode) {
        this(businessCode.getCode(), null, businessCode.getMessage());
    }
}
