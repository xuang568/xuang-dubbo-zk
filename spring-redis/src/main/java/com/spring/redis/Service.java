package com.spring.redis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Service {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-redis.xml"});
        context.start();

        try {
            System.in.read(); // 按任意键退出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
