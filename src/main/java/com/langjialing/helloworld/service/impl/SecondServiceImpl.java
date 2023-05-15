package com.langjialing.helloworld.service.impl;

import com.langjialing.helloworld.service.IFirstService;
import com.langjialing.helloworld.service.ISecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 郎家岭伯爵
 * @time 2023/5/15 11:04
 */
@Service
public class SecondServiceImpl implements ISecondService {

    @Autowired
    private IFirstService firstService;

    @Override
    public void sayHello() {
        System.out.println("Hello secondService!");
    }

    @Override
    public void callFirstService() {
        firstService.sayHello();
    }
}
