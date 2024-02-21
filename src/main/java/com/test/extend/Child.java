package com.test.extend;

public class Child extends Parent{
    public static String static_name = "Child static_name";

    public static final String FINAL_NAME = "Child FINAL_NAME";
    public static final String FINAL_NAME3 = "Child FINAL_NAME";
    public static final int FINAL_NAME2 = 2;
    public void childMethod(){
        staticMethod();
        static_name = "static_name";
        System.out.println("childMethod");
        //staticParentInterfaceMethod();
    }

    public static void childStaticMethod() {
        System.out.println("child_staticMethod");
    }

    @Override
    public void method() {
        System.out.println("已重写");
    }



}
