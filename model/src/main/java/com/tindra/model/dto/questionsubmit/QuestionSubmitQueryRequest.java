package com.tindra.model.dto.questionsubmit;

import com.tindra.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询题目提交记录
 */
@Data
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {


    /**
     * 编程语言
     */
    private String language;

    /**
     * 题目提交状态
     */
    private Integer status;


    /**
     * 题目 id
     */
    private Long questionId;


    /**
     * 创建用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}