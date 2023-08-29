package com.langjialing.helloworld.controller;

import com.langjialing.helloworld.model.test.People;
import com.langjialing.helloworld.model.test.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郎家岭伯爵
 * @time 2023/7/26 19:13
 */
@RestController
public class TestController1 {

    @GetMapping("/test111")
    public void test111(){
        Student student = new Student();
        People people = new People();
        System.out.println(student instanceof People);
        System.out.println(people instanceof Student);
    }

}
