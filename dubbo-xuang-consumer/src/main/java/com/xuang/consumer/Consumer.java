package com.xuang.consumer;

import dubbo.xuang.serviceApi.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("dubboConsumer.xml");
        context.start();

        UserService userService = (UserService) context.getBean("userService");
        try {
            System.out.println("调用远程服务。。。。");
            Thread.sleep(5000);
            String sayHello = userService.sayHello("xunag");
            System.out.println("服务返回："+sayHello);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
