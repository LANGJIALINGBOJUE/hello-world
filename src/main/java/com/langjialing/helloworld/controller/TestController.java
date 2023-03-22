package com.langjialing.helloworld.controller;

import com.langjialing.helloworld.mapper.UserMapper;
import com.langjialing.helloworld.model.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 郎家岭伯爵
 * @time 2023/3/14 14:45
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/t1")
    public void test(@RequestBody UserEntity user){
        UserEntity user1 = new UserEntity().setUserName("langjialing");

        log.info(user1.getUserName());
        user1.setUserName(user.getUserName());
        log.info(user1.getUserName());
    }

    @PostMapping("t2")
    public void test2(@RequestBody UserEntity user){
        userMapper.insertUser(user);
    }

    @GetMapping("/t3")
    public String test3(@RequestParam String s){
        return s;
    }
}
