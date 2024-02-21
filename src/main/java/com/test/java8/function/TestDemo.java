package com.test.java8.function;

public class TestDemo {
    public static String getStatic(){
        return "static";
    }
    public String name = "Tom";

    public String getName(){
        return this.name;
    }

    public Integer age = 10;

    public Integer getAge(){
        return this.age;
    }


}
