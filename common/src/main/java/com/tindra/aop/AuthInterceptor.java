//package com.tindra.aop;
//
//
//import com.tindra.annotation.AuthCheck;
//import com.tindra.common.BusinessCode;
//import com.tindra.exception.BusinessException;
//import com.tindra.model.entity.User;
//import com.tindra.model.enums.UserRoleEnum;
//import com.tindra.serviceclient.service.UserFeign;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 权限校验拦截器
// *
// */
//@Aspect
//@Component
//public class AuthInterceptor {
//
//    @Resource
//    private UserFeign userFeign;
//
//    /**
//     * 执行拦截
//     *
//     * @param joinPoint -被拦截的方法
//     * @param authCheck -注解
//     * @return
//     */
//    @Around("@annotation(authCheck)")
//    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
//        String mustRole = authCheck.mustRole();
//        //  获取当前请求的 HttpServletRequest 对象
//        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        // 当前登录用户
//        User loginUser = userFeign.getLoginUser(request);
//        // 必须有该权限才通过
//        if (StringUtils.isNotBlank(mustRole)) {
//            UserRoleEnum mustUserRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
//            if (mustUserRoleEnum == null) {
//                throw new BusinessException(BusinessCode.NO_AUTH_ERROR);
//            }
//            String userRole = loginUser.getUserRole();
//            // 如果被封号，直接拒绝
//            if (UserRoleEnum.BAN.equals(mustUserRoleEnum)) {
//                throw new BusinessException(BusinessCode.NO_AUTH_ERROR);
//            }
//            // 必须有管理员权限
//            if (UserRoleEnum.ADMIN.equals(mustUserRoleEnum)) {
//                if (!mustRole.equals(userRole)) {
//                    throw new BusinessException(BusinessCode.NO_AUTH_ERROR);
//                }
//            }
//        }
//        // 通过权限校验，放行
//        return joinPoint.proceed();
//    }
//}
//
