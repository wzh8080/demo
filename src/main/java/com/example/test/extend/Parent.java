package com.example.test.extend;

public class Parent implements ParentInterface{
    public String public_name;
    private String private_name;
    protected String protected_name;
    public static String static_name = "Parent static_name";

    public static final String FINAL_NAME = "Parent FINAL_NAME";
    public static final String FINAL_NAME3 = "Parent FINAL_NAME";
    public static final int FINAL_NAME2 = 1;

    public void method() {
        System.out.println("Parent method");
    }
    public static void staticMethod() {
        System.out.println("Parent staticMethod");
    }
    public final static void staticFinalMethod() {
        System.out.println("Parent staticFinalMethod");
    }
    public final void finalMethod() {
        System.out.println("Parent finalMethod");
    }

    private void privateMethod() {
        System.out.println("Parent privateMethod");
    }
}
