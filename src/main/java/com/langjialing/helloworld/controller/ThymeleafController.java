package com.langjialing.helloworld.controller;

import com.langjialing.helloworld.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

/**
 * @author 郎家岭伯爵
 */
@Controller
public class ThymeleafController {

    @Autowired
    TestService testService;

    @RequestMapping("thymeleaf")
    public String test(Model model){
        testService.getRestTemplate();
        model.addAttribute("msg", "Hello,Thymeleaf!");
        return "Test";
    }

}
