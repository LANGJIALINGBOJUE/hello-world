package com.langjialing.helloworld1;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloWorld1ApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testRabbitmq() {
        String queueName = "test";

        for (int i = 0; i < 50; i++) {
            String message = "Hello,Rabbitmq_" + i;
            System.out.println(message);
            rabbitTemplate.convertAndSend(queueName, message);
        }
    }

}
