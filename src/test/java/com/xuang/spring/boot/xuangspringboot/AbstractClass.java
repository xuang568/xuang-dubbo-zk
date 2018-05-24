package com.xuang.spring.boot.xuangspringboot;


/*
* 抽象类 与接口的区别  单继承，多实现
* */
public abstract class AbstractClass  extends Demo6 implements DemoInterface{
    abstract String get();
    String say(){
        return "";
    };
}
