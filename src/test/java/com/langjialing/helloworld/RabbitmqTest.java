package com.langjialing.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/3 10:42
 */
@SpringBootTest
public class RabbitmqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
