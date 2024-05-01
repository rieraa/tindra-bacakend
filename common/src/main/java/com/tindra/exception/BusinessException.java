package com.tindra.exception;

import com.tindra.common.BusinessCode;

/**
 * 自定义异常类
 *
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(BusinessCode businessCode) {
        super(businessCode.getMessage());
        this.code = businessCode.getCode();
    }

    public BusinessException(BusinessCode businessCode, String message) {
        super(message);
        this.code = businessCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
