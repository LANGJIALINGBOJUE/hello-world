package com.langjialing.helloworld1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/3 11:31
 */
@RestController
@RequestMapping("/test1")
public class HelloWorldController {
    @GetMapping("/t1")
    public void test1(){
        System.out.println("t1");
    }
}
