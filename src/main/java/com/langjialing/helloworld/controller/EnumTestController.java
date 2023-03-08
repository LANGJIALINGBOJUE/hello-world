package com.langjialing.helloworld.controller;

import com.langjialing.helloworld.model.enums.EnumTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author 郎家岭伯爵
 * @date 2023/3/6 9:22
 */
@RestController
public class EnumTestController {
    @GetMapping("/enum")
    public HashMap<String, String> testEnum(String s){
        return EnumTest.getCodeAndNameByStrings(s);
    }
}
