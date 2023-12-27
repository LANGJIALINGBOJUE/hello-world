package com.langjialing.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloWorldApplicationTests {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Test
    void test1(){
        System.out.println(redisHost);
    }

}
