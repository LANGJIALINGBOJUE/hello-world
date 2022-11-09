package com.langjialing.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/hello")
    public String hello() {
        logger.info("This is a info message!");
        logger.warn("This is a warn message!");
        logger.error("This is a error message!");
        return "Hello,World!";
    }

}
