package com.tindra.model.vo;

import cn.hutool.json.JSONUtil;
import com.tindra.model.dto.jundge.JudgeConfig;
import com.tindra.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目封装类
 */
@Data
public class QuestionVO implements Serializable {
    /**
     * id
     */
    private Long id;

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
    private List<String> tags;


    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;


    /**
     * 判题配置（json 对象）
     */
    private JudgeConfig judgeConfig;

    /**
     * 创建者 id
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建者信息
     */
    private UserVO userVO;

    /**
     * 包装类转对象
     *
     * @param questionVO 前端返回
     * @return
     */
    public static Question voToObj(QuestionVO questionVO) {
        if (questionVO == null) {
            return null;
        }
        Question question = new Question();
        // 复制同名属性
        BeanUtils.copyProperties(questionVO, question);
        // 将标签列表转为json字符串
        List<String> tagList = questionVO.getTags();
        question.setTags(JSONUtil.toJsonStr(tagList));
        // 将判题配置转为json字符串
        JudgeConfig judgeConfig = questionVO.getJudgeConfig();
        if (judgeConfig != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(judgeConfig));
        }
        return question;
    }

    /**
     * 对象转包装类 用于前端展示
     *
     * @param question
     * @return
     */
    public static QuestionVO objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        // 将标签json字符串转为列表便于前端展示
        List<String> tagList = JSONUtil.toList(question.getTags(), String.class);
        questionVO.setTags(tagList);
        // 将判题配置json字符串转为对象便于前端展示
        JudgeConfig config = JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class);
        questionVO.setJudgeConfig(config);
        return questionVO;
    }


    private static final long serialVersionUID = 1L;


}