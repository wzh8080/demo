package com.test.extend;
@FunctionalInterface
public interface InterfaceTest {
    void method();
    default void defaultMethod() {
        System.out.println("default");
    }
    static void staticMethod() {
        System.out.println("static");
    }
}
