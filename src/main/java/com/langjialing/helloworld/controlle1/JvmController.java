package com.langjialing.helloworld.controlle1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/22 10:05
 */
@RestController
@RequestMapping("/jvm")
public class JvmController {

    private HashMap<Long, Object> userCache = new HashMap<>(10);

    @GetMapping("/login")
    public void testLogin(Long id){
        userCache.put(id, new byte[1024 * 1024 * 100]);
        System.out.println(userCache);
    }

    @GetMapping("/logout")
    public void testLogout(Long id){
        userCache.remove(id);
        System.out.println(userCache);
        // 手动请求gc。注意这里仅是请求JVM触发gc，并不是一定会回收出内存空间，这取决于是否有可回收的对象。
        System.gc();
    }
}
