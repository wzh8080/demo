package com.example.test.java8.stream;

import lombok.Data;

/**
 * @author 56465
 */
@Data
public class Student {
    private int score;
    private String name;
    private int age;
    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }
}
