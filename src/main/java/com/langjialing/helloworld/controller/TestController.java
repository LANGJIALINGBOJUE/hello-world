package com.langjialing.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

/**
 * @author 郎家岭伯爵
 */
@Controller
public class TestController {

    @RequestMapping("t1")
    public String test(Model model){

        model.addAttribute("msg", "Hello,Thymeleaf!");
        return "Test";
    }
}
