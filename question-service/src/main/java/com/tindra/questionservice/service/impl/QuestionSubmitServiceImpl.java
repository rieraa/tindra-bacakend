package com.tindra.questionservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tindra.common.BusinessCode;
import com.tindra.constant.CommonConstant;
import com.tindra.exception.BusinessException;
import com.tindra.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.tindra.model.dto.questionsubmit.QuestionSubmitRequest;
import com.tindra.model.entity.Question;
import com.tindra.model.entity.QuestionSubmit;
import com.tindra.model.entity.User;
import com.tindra.model.enums.QuestionSubmitStatusEnum;
import com.tindra.model.vo.QuestionSubmitVO;
import com.tindra.questionservice.mapper.QuestionSubmitMapper;
import com.tindra.questionservice.rabbit.Producer;
import com.tindra.questionservice.service.QuestionService;
import com.tindra.questionservice.service.QuestionSubmitService;
import com.tindra.serviceclient.service.JudgeFeign;
import com.tindra.serviceclient.service.UserFeign;
import com.tindra.utils.SqlUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {
    @Resource
    private QuestionService questionService;

    @Resource
    private UserFeign userFeign;

    @Resource
    @Lazy
    private JudgeFeign judgeFeign;

    @Resource
    private Producer producer;

    /**
     * 提交题目
     *
     * @param questionSubmitRequest 提交的题目信息
     * @param loginUser             登录用户
     * @return 提交的题目记录 id
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitRequest questionSubmitRequest, User loginUser) {
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionSubmitRequest.getQuestionId());
        if (question == null) {
            throw new BusinessException(BusinessCode.NOT_FOUND_ERROR);
        }
        // 是否已登录
        long userId = loginUser.getId();
        // 每个用户串行提交题目
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setLanguage(questionSubmitRequest.getLanguage());
        questionSubmit.setCode(questionSubmitRequest.getCode());
        questionSubmit.setQuestionId(questionSubmitRequest.getQuestionId());
        // todo 题目初始状态
        //questionSubmit.setStatus(0);
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getCode());
        questionSubmit.setJudgeInfo("{}");
        QueryWrapper<QuestionSubmit> questionSubmitQueryWrapper = new QueryWrapper<>(questionSubmit);
        boolean flag = this.save(questionSubmit);
        if (!flag) {
            throw new BusinessException(BusinessCode.SYSTEM_ERROR, "提交题目插入失败");
        }
        // 异步判题
        Long questionSubmitId = questionSubmit.getId();
        // 通过消息队列异步判题
        producer.sendMessage("exchange", "judge", String.valueOf(questionSubmitId));
        //judgeFeign.judgeCode(questionSubmitId);

        return questionSubmit.getId();
    }


    /**
     * 获取查询包装类（用户根据哪些字段查询，根据前端传来的请求对象，得到 mybatis 框架支持的查询 QueryWrapper 类）
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null) {
            return queryWrapper;
        }
        String language = questionSubmitQueryRequest.getLanguage();
        Integer status = questionSubmitQueryRequest.getStatus();
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        Long userId = questionSubmitQueryRequest.getUserId();
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();

        // 拼接查询条件
        queryWrapper.eq(StringUtils.isNotBlank(language), "language", language);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
        queryWrapper.eq(QuestionSubmitStatusEnum.getEnumByValue(status) != null, "status", status);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        // 脱敏：仅本人和管理员能看见自己（提交 userId 和登录用户 id 不同）提交的代码
        long userId = loginUser.getId();
        // 处理脱敏
        if (userId != questionSubmit.getUserId() && !userFeign.isAdmin(loginUser)) {
            questionSubmitVO.setCode(null);
        }
        return questionSubmitVO;
    }

    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser) {
        List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
        Page<QuestionSubmitVO> questionSubmitVOPage = new Page<>(questionSubmitPage.getCurrent(), questionSubmitPage.getSize(), questionSubmitPage.getTotal());
        if (CollectionUtils.isEmpty(questionSubmitList)) {
            return questionSubmitVOPage;
        }
        List<QuestionSubmitVO> questionSubmitVOList = questionSubmitList.stream()
                .map(questionSubmit -> getQuestionSubmitVO(questionSubmit, loginUser))
                .collect(Collectors.toList());
        questionSubmitVOPage.setRecords(questionSubmitVOList);
        return questionSubmitVOPage;
    }

}




