package com.langjialing.helloworld.controller;

import com.langjialing.helloworld.service.TokenUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 郎家岭伯爵
 * @time 2023/3/22 14:17
 */

@Slf4j
@RestController
public class TokenController {

    @Autowired
    private TokenUtilService tokenService;

    /**
     * 获取 Token 接口
     *
     * @return Token 串
     */
    @GetMapping("/token")
    public String getToken(@RequestParam String userId) {
        // 获取 Token 字符串，并返回
        return tokenService.generateToken(userId);
    }

    /**
     * 接口幂等性测试接口
     *
     * @param token 幂等 Token 串
     * @return 执行结果
     */
    @PostMapping("/test")
    public String test(@RequestHeader(value = "token") String token, @RequestParam String userId) {
        // 根据 Token 和与用户相关的信息到 Redis 验证是否存在对应的信息
        boolean result = tokenService.validToken(token, userId);
        // 根据验证结果响应不同信息
        return result ? "正常调用" : "重复调用或无效的userId";
    }

}
