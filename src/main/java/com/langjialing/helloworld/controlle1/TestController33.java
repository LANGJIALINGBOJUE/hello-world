package com.langjialing.helloworld.controlle1;

import cn.hutool.core.io.FileUtil;
import com.langjialing.helloworld.model.entity.UserEntity;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author 郎家岭伯爵
 * @time 2023/12/26 16:17
 */

@RestController
@RequestMapping("/test33")
public class TestController33 implements Runnable{
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.timeout}")
    private Integer redisTimeOut;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @GetMapping("/t1")
    public void test1(){
        JedisPool jedisPool = new JedisPool(new GenericObjectPoolConfig(), redisHost, redisPort, redisTimeOut, redisPassword);

        try(Jedis jedis = jedisPool.getResource()){
            // 存值
            jedis.set("langjialing111", "郎家岭伯爵111");

            // 判断是否存在
            Boolean keyExist = jedis.exists("langjialing111");
            System.out.println(keyExist);

            // 设置过期时间
            jedis.expire("langjialing111", 300);

            // 获取过期时间
            Long keyTtl = jedis.ttl("langjialing111");
            System.out.println(keyTtl);

            // 删除值
            jedis.del("langjialing111");

            // 一次存多个值
            jedis.mset("langjialing11", "郎家岭伯爵11", "langjialing22", "郎家岭伯爵22");
            // 一次获取多个值
            jedis.mget("langjialing11", "langjialing22");
        }
    }

    @GetMapping("/t2")
    public void test2(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        System.out.println(httpServletRequest.getRequestURL());
        System.out.println(Arrays.toString(httpServletRequest.getCookies()));
        System.out.println(httpServletRequest.getMethod());
        System.out.println(httpServletRequest.getQueryString());
        System.out.println(httpServletRequest.getRemoteAddr());
        System.out.println(httpServletRequest.getRemoteHost());

        httpServletResponse.setStatus(5);
    }

    @GetMapping("/t3")
    public String test3(){

        TestController33 t1 = new TestController33();

        Thread thread = new Thread(t1);
        thread.start();

        return "test3";
    }

    @Override
    public void run() {
        try{
            Thread.sleep(5000);
            System.out.println("Thread run()...");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/t4/{id}")
    public String test4(@PathVariable(value = "id") Integer id){
        if (id.equals(5)){
            return "普通用户";
        }
        if (id.equals(10)){
            return "VIP用户";
        }
        return "异常用户";
    }

    @GetMapping("/t5")
    public void test5(){
        ClassLoader classLoader = UserEntity.class.getClassLoader();
        System.out.println(classLoader);

        ClassLoader classLoader1 = FileUtil.class.getClassLoader();
        System.out.println(classLoader1);
    }
}
