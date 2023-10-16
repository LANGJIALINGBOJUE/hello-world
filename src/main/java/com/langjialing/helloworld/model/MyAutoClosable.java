package com.langjialing.helloworld.model;

/**
 * @author 郎家岭伯爵
 * @time 2023/9/20 9:57
 */
public class MyAutoClosable implements AutoCloseable {
    public void doIt() {
        System.out.println("MyAutoClosable doing it!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("MyAutoClosable closed!");
    }
}
