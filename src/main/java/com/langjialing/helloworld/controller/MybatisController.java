package com.langjialing.helloworld.controller;

import com.langjialing.helloworld.mapper.UserMapper;
import com.langjialing.helloworld.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 郎家岭伯爵
 * @time 2023/3/13 16:19
 */
@RestController
@RequestMapping("/mybatis")
public class MybatisController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/mybatis")
    public List<UserEntity> mybatisTest(@RequestParam Long age){
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(age);
        return userMapper.getUserList(userEntity);
    }
}
