package com.xuang.spring.redis;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDao {
    @Autowired
    private RedisMannager redisMannager;

    public void setRedisMannager() {
       
    }
}
