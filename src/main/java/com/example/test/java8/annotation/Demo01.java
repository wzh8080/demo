package com.example.test.java8.annotation;

/**
 * 配置多个重复的注解
 */
@MyTest("tbc")
@MyTest("tba")
@MyTest("tba")
public class Demo01 {
    @MyTest("mbc")
    @MyTest("mba")
    public void test() throws NoSuchMethodException {
    }
}