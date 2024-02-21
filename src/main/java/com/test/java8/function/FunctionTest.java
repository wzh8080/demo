package com.test.java8.function;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author 56465
 */
public class FunctionTest {
    @Test
    public void supplierTest(){
        Supplier<String> s1 = () -> "abc";
        System.out.println(s1.get());
        System.out.println("---------------------类名::静态方法");
        Supplier<Long> s2 = System::currentTimeMillis;
        System.out.println("s2.get() = " + s2.get());
        System.out.println("---------------------类名::实例方法");
        TestDemo demo = new TestDemo();
        Supplier<String> s3 = demo::getName;
        Supplier<String> s4 = TestDemo::getStatic;
        //Supplier<String> s5 = TestDemo::getName;
        //Function<String, Integer> f2 = TestDemo::getAge;

        Supplier<TestDemo> s6 = TestDemo::new;
        TestDemo demo1 = s6.get();

        // String[]::new 调用数组的构造器`TypeName[]::new`
        // 有参构造器，不能用 Supplier
        Supplier<String[]> s8 = () -> new String[10];
        Function<Integer, String[]> fun2 = String[]::new;

        // 类名 :: 实例方法
        Function<String, Integer> f2 = String::length;
        Supplier<Integer> s7 = () -> new TestDemo().getAge();
        Supplier<Integer> s9 = new TestDemo()::getAge;
        Function<TestDemo, Integer> f3 = TestDemo::getAge;

    }
    @Test
    public void predicateTest(){
        Predicate<String> p1 = s -> s.length() > 3;
        System.out.println(p1.test("ab"));
        System.out.println(p1.test("abc"));
        System.out.println(p1.test("abcd"));
        System.out.println(p1.test("abcde"));
        System.out.println("---------------------");
        Predicate<String> p2 = p1.and(s -> s.length() < 5);
        System.out.println(p2.test("ab"));
        System.out.println(p2.test("abc"));
        System.out.println(p2.test("abcd"));
        System.out.println(p2.test("abcde"));
    }
}
