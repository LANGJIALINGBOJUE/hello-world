package com.langjialing.helloworld.controller;

import com.langjialing.helloworld.config.response.ResponseResult;
import com.langjialing.helloworld.mapper.UserMapper;
import com.langjialing.helloworld.model.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.LRUMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郎家岭伯爵
 * @time 2023/3/22 9:30
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 最大容量 100 个，根据 LRU 算法淘汰数据的 Map 集合
     */
    private LRUMap<String, Integer> reqCache = new LRUMap<>(100);

    @GetMapping("/add")
    public ResponseResult<String> addUser(@RequestParam String userId){

        // 非空判断(忽略)...
        synchronized (this.getClass()) {
            // 重复请求判断
            if (reqCache.containsKey(userId)) {
                // 重复请求
                log.info("请勿重复提交：{}", userId);
                return ResponseResult.fail("执行失败！");
            }
            // 存储请求 ID
            reqCache.put(userId, 1);
        }
        // 业务代码...
        log.info("成功添加用户:{}", userId);
//        return "执行成功！";
        return ResponseResult.success("执行成功！");
    }

    @GetMapping("/addUser")
    public Integer addUser(){
        UserEntity userEntity = new UserEntity().setUserName("郎家岭伯爵").setPassword("123456").setAge(18L);
        userMapper.insertUser(userEntity);
        return userEntity.getId();
    }
}
