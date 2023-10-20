package com.langjialing.helloworld.controlle1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 郎家岭伯爵
 * @time 2023/10/20 15:50
 */
@Controller
@RequestMapping("/test2")
public class TestController2 {

    @GetMapping("/t1")
    public String test1(){
        return "test1";
    }
}
