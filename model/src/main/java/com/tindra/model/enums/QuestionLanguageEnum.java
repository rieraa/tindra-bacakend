package com.tindra.model.enums;

import org.apache.commons.lang3.ObjectUtils;

/**
 * 题目提交使用的编程语言枚举
 * code 存储在数据库中的
 * value 用于展示
 */
public enum QuestionLanguageEnum {

    JAVA_SCRIPT("javascript", "javascript"),
    JAVA("java", "java"),
    C("c", "c"),
    GOLANG("go", "go");

    private final String value;

    private final String code;

    QuestionLanguageEnum(String value, String code) {
        this.value = value;
        this.code = code;
    }


    /**
     * 根据 code 获取枚举
     *
     * @param code
     * @return
     */
    public static QuestionLanguageEnum getEnumByValue(String code) {
        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        for (QuestionLanguageEnum questionLanguageEnum : QuestionLanguageEnum.values()) {
            if (questionLanguageEnum.code.equals(code)) {
                return questionLanguageEnum;
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
