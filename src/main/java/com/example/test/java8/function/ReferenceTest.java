package com.example.test.java8.function;

import org.junit.Test;

import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ReferenceTest extends Object{
    @Test
    /**
     * 对象::方法名
     */
    public void test01() {
        Date now = new Date();
        Supplier<Long> supp = () -> {
            return now.getTime();
        };
        System.out.println(supp.get());

        Supplier<Long> supp2 = now::getTime;
        System.out.println(supp2.get());
    }

    /**
     * 类名::静态方法
     */
    @Test
    public void test02() {
        Supplier<Long> supp = () -> {
            return System.currentTimeMillis();
        };
        System.out.println(supp.get());
        Supplier<Long> supp2 = System::currentTimeMillis;
        System.out.println(supp2.get());
    }

    @Test
    /**
     * 类名::实例方法
     */
    public void test03() {

        Function<String, Integer> f2 = String::length;
        System.out.println(f2.apply("abc"));
        BiFunction<String, Integer, String> bif = String::substring;
        String hello = bif.apply("hello", 2);
        System.out.println("hello = " + hello);
    }
}
