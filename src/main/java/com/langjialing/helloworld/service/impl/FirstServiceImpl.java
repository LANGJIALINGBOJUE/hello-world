package com.langjialing.helloworld.service.impl;

import com.langjialing.helloworld.service.IFirstService;
import com.langjialing.helloworld.service.ISecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 郎家岭伯爵
 * @time 2023/5/15 11:03
 */
@Service
public class FirstServiceImpl implements IFirstService {

    @Autowired
    private ISecondService secondService;

    @Override
    public void sayHello() {
        System.out.println("Hello firstService!");
    }

    @Override
    public void callSecondService() {
        secondService.sayHello();
    }
}
