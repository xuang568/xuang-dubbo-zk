package com.xuang.spring.boot.xuangspringboot;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/")
    private String get(){
        return "Greetings from Spring Boot!";
    }


}
