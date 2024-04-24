package com.tindra.model.enums;

import org.apache.commons.lang3.ObjectUtils;

/**
 * 题目提交状态枚举
 * code 存储在数据库中的
 * value 用于展示
 *
 */

public enum QuestionSubmitStatusEnum {

    WAITING("等待中", 0),
    IN_QUEUE("判题队列中", 1),
    COMPLETED("已完成", 2),
    FAIL("代码运行错误", 3);


    private final String value;
    private final int code;

    QuestionSubmitStatusEnum(String value, int code) {
        this.value = value;
        this.code = code;
    }

    public String getDescription() {
        return value;
    }

    public int getCode() {
        return code;
    }

    /**
     * 根据 code 获取枚举
     *
     * @param code
     * @return
     */
    public static QuestionSubmitStatusEnum getEnumByValue(Integer code) {
        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        for (QuestionSubmitStatusEnum questionSubmitStatusEnum : QuestionSubmitStatusEnum.values()) {
            if (questionSubmitStatusEnum.code == code) {
                return questionSubmitStatusEnum;
            }
        }
        return null;
    }
}
