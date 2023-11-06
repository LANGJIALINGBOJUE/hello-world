package com.langjialing.helloworld.controlle1;

import com.alibaba.fastjson.JSON;
import com.langjialing.helloworld.model.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郎家岭伯爵
 * @time 2023/10/20 15:50
 */
@RestController
@RequestMapping("/test22")
public class TestController22 {

    @GetMapping("/t1")
    public String test1(){
        return "test1";
    }

    @GetMapping("/t2")
    public void test2(){
        String s = "{\"userName\": \"郎家岭伯爵\", \"password\": \"123456\"}";
        UserEntity userEntity = JSON.parseObject(s, UserEntity.class);
        System.out.println(userEntity.getUserName());
        System.out.println(userEntity.getPassword());
        System.out.println(userEntity.getId());
    }
}
