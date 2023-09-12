package com.langjialing.helloworld.controller;

import com.langjialing.helloworld.model.test.People;
import com.langjialing.helloworld.model.test.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 郎家岭伯爵
 * @time 2023/7/26 19:13
 */
@RestController
@RequestMapping("/test2")
@Slf4j
public class TestController1 {

    @GetMapping("/test111")
    public void test111(){
        Student student = new Student();
        People people = new People();
        System.out.println(student instanceof People);
        System.out.println(people instanceof Student);
    }

    @GetMapping("t2")
    public void test2(){
        List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        list.forEach(item -> {
            if (item == 3){
                return;
            }
            log.info("item:{}", item);
        });
    }

}
