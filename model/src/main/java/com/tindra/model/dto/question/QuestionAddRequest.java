package com.tindra.model.dto.question;

import com.tindra.model.dto.jundge.JudgeCase;
import com.tindra.model.dto.jundge.JudgeConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建请求
 */
@Data
public class QuestionAddRequest implements Serializable {


    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    // todo 为什么使用list string
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;


    /**
     * 判题用例（json 数组）
     */
    private List<JudgeCase> judgeCase;


    /**
     * 判题配置（json 对象）
     */
    private JudgeConfig judgeConfig;

    private static final long serialVersionUID = 1L;
}