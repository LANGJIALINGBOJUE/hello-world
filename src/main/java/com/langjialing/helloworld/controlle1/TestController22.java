package com.langjialing.helloworld.controlle1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.langjialing.helloworld.mapper.UserMapper;
import com.langjialing.helloworld.model.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 郎家岭伯爵
 * @time 2023/10/20 15:50
 */
@RestController
@RequestMapping("/test22")
public class TestController22 {

    @Resource
    private UserMapper userMapper;

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

        System.out.println(userEntity.toString());
    }

    @GetMapping("/t3")
    public void test3(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", "langjialing");
        jsonObject.put("password", "123456");

        System.out.println(JSON.toJSONString(jsonObject));
        System.out.println(jsonObject.get("userName"));

    }

    @GetMapping("/t4")
    public void test4(){
        List<UserEntity> list = userMapper.getUserList(new UserEntity().setAge(222L));
        System.out.println(list);
        System.out.println(list.size());
    }

    @GetMapping("/t5")
    public void test5(){
        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> map1 = new HashMap<>(10);
        map1.put("langjialing", "1");
        Map<String, String> map2 = new HashMap<>(10);
        map2.put("langjialing", "2");
        Map<String, String> map3 = new HashMap<>(10);
        map3.put("langjialing", "3");

        list.add(map1);
        list.add(map2);
        list.add(map3);

        List<String> langjialing = list.stream().map(map -> map.get("langjialing")).collect(Collectors.toList());
        System.out.println(langjialing);

        List<String> list1 = new ArrayList<>();
        list1.addAll(list.stream().map(map -> map.get("langjialing")).collect(Collectors.toList()));
        System.out.println(list1);
    }
}
