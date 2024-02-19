package com.example.test.java8.function;


import java.util.Arrays;
import java.util.function.Function;

public class ConsumerTest {
    public static void main(String[] args) {
//        Consumer<String> c1 =
//                (x) -> System.out.println("c1 转大写：" + x.toUpperCase());
//        Consumer<String> c2 =
//                (x) -> System.out.println("c2 转小写：" + x.toLowerCase());
//        c1.accept("qwer");
//        c2.accept("qwer");
//        Consumer<String> c3 = c1.andThen(c2).andThen(c1);
//        c3.accept("qwer");

        System.out.println(Function.identity());
        Function<String, Integer> f1 = (x) -> {
            System.out.println("字符串 转换为 数字");
            return Integer.parseInt(x);
        };
        System.out.println(Function.identity());

//        Integer test = f1.apply("6");
//        System.out.println("测试" + test);
        System.out.println("----"+Function.identity());

        Function<String, int[]> then = f1.andThen((x) -> {
                    System.out.println("数字 加2");
                    return x + 2;
                }).andThen((x) -> {
                    System.out.println("数字 转换为 数组");
                    int[] arr = new int[x];
                    for (int i = 0; i < x; i++) {
                        arr[i] = i;
                    }
                    return arr;
                });


        int[] arr = then.apply("2");
        System.out.println(Arrays.toString(arr));

    }
}
