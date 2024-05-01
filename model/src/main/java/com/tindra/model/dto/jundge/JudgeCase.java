package com.tindra.model.dto.jundge;

import lombok.Data;

/**
 * 判题用例
 */
@Data
public class JudgeCase {
    /**
     * 输入
     */
    private String input;
    /**
     * 输出
     */
    private String output;

    /**
     * 是否可见
     */
    private Boolean visible;
}
