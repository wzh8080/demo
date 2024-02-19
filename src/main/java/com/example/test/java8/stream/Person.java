package com.example.test.java8.stream;

import lombok.Data;

import java.util.Objects;

@Data
public class Person {
    private String name;
    private int age;
    private String idCard;

    public Person(String name, int age, String idCard) {
        this.name = name;
        this.age = age;
        this.idCard = idCard;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.idCard = System.currentTimeMillis() + "";
    }
    @Override
    public int hashCode() {
        return super.hashCode();
        //return Objects.hash(idCard);
    }


    @Override
    public boolean equals(Object obj) {
        // 如果传入的对象为 null，或者不属于同一类型，则两个对象不相等
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // 将传入的对象转换为 Person 类的实例
        Person other = (Person) obj;

        // 比较两个对象的属性值
        return Objects.equals(idCard, other.idCard);
    }
}
