package com.tindra.model.sandbox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 判题信息（内存、时间、执行信息）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JudgeInfo {

    /**
     * 代码执行信息
     */
    private String message;
    /**
     * 执行消耗内存
     */
    private Long memory;
    /**
     * 执行消耗时间
     */
    private Long time;
}
