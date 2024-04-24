package com.tindra.userservice.controller.inside;

import com.tindra.model.entity.User;
import com.tindra.serviceclient.service.UserFeign;
import com.tindra.userservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 内部服务 仅内部调用
 *
 */
@RestController
@RequestMapping("/private")
public class UserPrivateController implements UserFeign {

    @Resource
    UserService userService;

    /**
     * 根据用户id获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/get/id")
    @Override
    public User getById(@RequestParam("userId") Long userId){
        return userService.getById(userId);
    }


    /**
     * 根据用户id集合获取用户信息
     *
     * @param idList
     * @return
     */
    @GetMapping("/get/idList")
    @Override
    public List<User> listByIds(@RequestParam("idList") Collection<Long> idList){
        return userService.listByIds(idList);
    }

}
