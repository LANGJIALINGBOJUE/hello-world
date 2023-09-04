package com.langjialing.helloworld.service.impl;

import com.langjialing.helloworld.service.IFirstService;
import com.langjialing.helloworld.service.ISecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 郎家岭伯爵
 * @time 2023/5/15 11:04
 */
@Service
public class SecondServiceImpl implements ISecondService {


    @Override
    public void sayHello() {
        System.out.println("Hello secondService!");
    }

    @Override
    public void callFirstService() {

    }

}
