package com.langjialing.helloworld.controlle1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/3 10:39
 */
@Component
public class RabbitmqController {

    @RabbitListener(queues = "test")
    public void listenQueueMessage1(String msg) throws InterruptedException {
        System.out.println("spring 消费者1接收到消息：【" + msg + "】");
        Thread.sleep(20);
    }

    @RabbitListener(queues = "test")
    public void listenQueueMessage2(String msg) throws InterruptedException {
        System.err.println("spring 消费者2接收到消息------：【" + msg + "】");
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) throws InterruptedException {
        System.out.println("spring 消费者1接收到Fanout消息：【" + msg + "】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) throws InterruptedException {
        System.err.println("spring 消费者2接收到Fanout消息：【" + msg + "】");
    }

    @RabbitListener(queues = "topic.queue1")
    public void listenTopicQueue1(String msg) throws InterruptedException {
        System.out.println("spring 消费者1接收到Topic消息：【" + msg + "】");
    }

    @RabbitListener(queues = "topic.queue2")
    public void listenTopicQueue2(String msg) throws InterruptedException {
        System.err.println("spring 消费者2接收到Topic消息：【" + msg + "】");
    }
}
