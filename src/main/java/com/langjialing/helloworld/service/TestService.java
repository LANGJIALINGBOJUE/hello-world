package com.langjialing.helloworld.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author 郎家岭伯爵
 */
@Service
public class TestService {

    @Value("${spring.application.name}")
    String name;

    public RestTemplate getRestTemplate(){
        System.out.println("RestTemplate：" + name);
        return new RestTemplate();
    }
}
