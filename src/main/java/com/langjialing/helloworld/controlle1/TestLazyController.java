package com.langjialing.helloworld.controlle1;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/31 15:29
 */
@RestController
@RequestMapping("/testLazy")
@Lazy
public class TestLazyController {
    private final byte[] bytes = new byte[1024 * 1024 * 1024];
}
