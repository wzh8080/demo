package com.example.test.java8.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 重复的注解容器注解
 */
@Retention(RetentionPolicy.RUNTIME)
@interface MyTests {
    MyTest[] value();
}
