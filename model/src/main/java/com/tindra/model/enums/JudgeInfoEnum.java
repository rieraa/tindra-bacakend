package com.tindra.model.enums;

import org.apache.commons.lang3.ObjectUtils;

/**
 * 判题结果枚举
 * code 存储在数据库中的
 * value 用于展示
 */
public enum JudgeInfoEnum {

    ACCEPTED("题目通过", "ACCEPTED"),
    WRONG_OUTPUT("与输出用例不符", "WRONG_OUTPUT"),
    COMPILE_ERROR("编译错误", "COMPILE_ERROR"),
    MEMORY_LIMIT_EXCEEDED("内存溢出", "MEMORY_LIMIT_EXCEEDED"),
    TIME_LIMIT_EXCEEDED("运行超时", "TIME_LIMIT_EXCEEDED"),
    OUTPUT_LIMIT_EXCEEDED("输出过长", "OUTPUT_LIMIT_EXCEEDED"),
    DANGEROUS_OPERATION("危险操作", "DANGEROUS_OPERATION"),
    RUNTIME_ERROR("运行错误", "RUNTIME_ERROR"),
    SYSTEM_ERROR("系统错误", "SYSTEM_ERROR");

    private final String value;
    private final String code;

    JudgeInfoEnum(String value, String code) {
        this.value = value;
        this.code = code;
    }

    /**
     * 根据 code 获取枚举
     *
     * @param code
     * @return
     */
    public static JudgeInfoEnum getEnumByValue(String code) {
        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        for (JudgeInfoEnum judgeInfoEnum : JudgeInfoEnum.values()) {
            if (judgeInfoEnum.code.equals(code)) {
                return judgeInfoEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return code;
    }

    public String getText() {
        return value;
    }
}
