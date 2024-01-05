package com.langjialing.helloworld1;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testRabbitmqFanout() {
        // 指定交换机名称
        String exchangeName = "langjialing.fanout";

        String message = "Hello,Rabbitmq_Fanout";
        System.out.println(message);
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testRabbitmqTopic() {
        // 指定交换机名称
        String exchangeName = "langjialing.topic";

        String message = "Hello,Rabbitmq_Fanout";
        System.out.println(message);
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", message);
    }

    @Test
    public void testObjectRabbitmqTopic() {
        // 指定交换机名称
        String exchangeName = "langjialing.topic";

        Map<String, Object> map = new HashMap<>(2);
        map.put("name", "小明");
        map.put("age", 21);

        System.out.println(map);
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", map);
    }

    @Test
    void testPageOut(){
        Message message = MessageBuilder
                .withBody("hello".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        for (int i = 0; i < 1000000; i++) {
//            rabbitTemplate.convertAndSend("langjialing.topic", "china.sport", message);
            rabbitTemplate.convertAndSend("topic.queue1",  message);

        }

        List<Integer> list = new ArrayList<>();
    }
}
