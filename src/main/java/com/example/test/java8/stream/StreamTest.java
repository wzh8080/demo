package com.example.test.java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * @author 56465
 */
public class StreamTest {
    /**
     * 示例
     */
    @Test
    public void test01() {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.stream()
                .filter(s -> s.startsWith("张"))
                .filter(s -> s.length() == 3)
                .forEach(System.out::println);
    }

    /**
     * 创建 Stream
     */
    @Test
    public void test02() {
        // Stream of 获取 Stream
        System.out.println("----------------字符串：----------------");
        Stream<String> stream1 = Stream.of("张无忌", "周芷若", "赵敏");
        stream1.forEach(System.out::println);

        System.out.println("----------------字符串数组：----------------");
        String[] strArr = new String[]{"张无忌", "周芷若", "赵敏"};
        Stream<String> stream2 = Stream.of(strArr);
        stream2.forEach(System.out::println);

        System.out.println("----------------基本数据类型数组：（不可以）----------------");
        int[] intArr1 = {1, 2, 3, 4, 5};
        Stream<int[]> stream3 = Stream.of(intArr1);
        stream3.forEach(System.out::println);
        // 打印：[I@5a42bbf4
        // 基本数据类型数组 装箱后可以使用 Stream.of()
        Integer[] intArr2 = {1, 2, 3, 4, 5};
        Stream<Integer> stream4 = Stream.of(intArr2);
        stream4.forEach(System.out::println);
        // 但是 Integer占用的内存比int多,在Stream流操作中会自动装箱和拆箱
        Stream<Integer> stream5 = Arrays.stream(intArr2);
        IntStream intStream = stream5.mapToInt(Integer::intValue);
        // Integer 转成 int 继续操作
        // ...
        // int 也可再装箱成 Integer
        Stream<Integer> boxed = intStream.boxed();
        boxed.forEach(s -> System.out.println(s.getClass() + ", " + s));
    }

    /**
     * forEach 方法测试
     * 该方法接收一个 Consumer 接口函数，会将每一个流元素交给该函数进行处理。例如：
     */
    @Test
    public void test03() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,  "苏星河", "老子", "庄子", "孙子");
        list.stream().forEach((String s) -> {
            System.out.println(s);
        });
        // 简写
        list.stream().forEach(s -> System.out.println(s));
        // 简写
        list.stream().forEach(System.out::println);
        // （迭代器方法）简写 ''stream().forEach()'' can be replaced with 'forEach()''
        list.forEach(System.out::println);
    }

    /**
     * count 方法测试
     */
    @Test
    public void test04() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "迪丽热巴", "宋远桥", "苏星河", "老子", "庄子", "孙子");
        System.out.println(list.stream().count());
    }

    /**
     * filter 方法测试
     */
    @Test
    public void test05() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "宋远桥", "苏星河", "老狗");
        list.stream().filter(s -> s.startsWith("苏")).forEach(System.out::println);
    }

    @Test
    public void test06() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "李四", "王五", "赵六");
        System.out.println("------------------ 前两个");
        //list.stream().limit(-1).forEach(System.out::println);
        System.out.println("------------------ 无打印");
        list.stream().limit(0).forEach(System.out::println);
        System.out.println("------------------ 打印全部（4个）");
        list.stream().limit(8).forEach(System.out::println);
    }

    @Test
    public void test07() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "李四", "王五", "赵六");
        // 跳过前两个
        list.stream().skip(2).forEach(System.out::println);
        // 全部跳过
        list.stream().skip(8).forEach(System.out::println);
        // 不可以小于0，可以等于0
    }

    @Test
    public void test08(){
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "李四", "王五", "赵六");
        System.out.println("--------------------");
        // 将字符串长度转换为整数
        list.stream().map(s -> s.length()).forEach(System.out::println);
        System.out.println("--------------------");
        // 简写
        list.stream().map(String::length).forEach(System.out::println);
    }

    @Test
    public void test09(){
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "王五a", "李四eeee", "赵六rr" ,"张三");
        // 默认排序
        list.stream().sorted().forEach(System.out::println);
        // 自定义排序：length 大的算大，从小到大排序。
        list.stream().sorted((a, b) -> a.length() - b.length()).forEach(System.out::println);
        //list.stream().sorted((a, b) -> b.length() - a.length()).forEach(System.out::println);
    }

    @Test
    public void test10(){
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "李四","李四", "王五", "赵六", "赵六");
        list.stream().distinct().forEach(System.out::println);

        System.out.println("-------------------");
        // 自定义类型去重
        Stream.of(
                new Person("张三", 18, "1111"),
                new Person("李四", 19, "2222"),
                new Person("王五", 20, "3333"),
                new Person("王五", 20, "3333"),
                new Person("李四", 19, "4444"),
                new Person("赵六", 21, "2222")
                ).distinct().forEach(System.out::println);
    }
    @Test
    public void test11(){
        Person per1 = new Person("李四", 19, "2222");
        Person per2 = new Person("lisi", 19, "2222");
        System.out.println(per1.equals(per2));
        System.out.println(per1.hashCode());
        System.out.println(per2.hashCode());
    }
    @Test
    public void test12(){
        boolean b = Stream.of(5, 3, 6, 1)
                 //.allMatch(e -> e > 0); // allMatch: 元素是否全部满足条件
                // .anyMatch(e -> e > 5); // anyMatch: 元素是否任意有一个满足条件
                .noneMatch(e -> e < 0); // noneMatch: 元素是否全部不满足条件
        System.out.println("b = " + b);
    }
    @Test
    public void test13(){
        Optional<Integer> first = Stream.of(2, 3, 6, 1).findFirst();
        System.out.println("first = " + first.get());
        Optional<Integer> any = Stream.of(5, 3, 6, 1).findAny();
        System.out.println("any = " + any.get());
    }

    @Test
    public void test14(){
        Optional<Integer> max = Stream.of(5, 3, 6, 1).max((o1, o2) -> o1 - o2);
        System.out.println("max = " + max.get());
        Optional<Integer> min = Stream.of(5, 3, 6, 1).min((o1, o2) -> o1 - o2);
        System.out.println("min = " + min.get());
    }

    @Test
    public void test15(){
        int reduce = Stream.of(4, 5, 3, 9)
                .reduce(0, (a, b) -> {
                    System.out.println("a = " + a + ", b = " + b);
                    return a + b;
                });
        // reduce:
        // 第一次将默认做赋值给x, 取出第一个元素赋值给y,进行操作
        // 第二次,将第一次的结果赋值给x, 取出二个元素赋值给y,进行操作
        // 第三次,将第二次的结果赋值给x, 取出三个元素赋值给y,进行操作
        // 第四次,将第三次的结果赋值给x, 取出四个元素赋值给y,进行操作
        System.out.println("reduce = " + reduce);
        System.out.println("---------------------");
        int reduce2 = Stream.of(4, 5, 3, 9)
                .reduce(0, (x, y) -> {
                    return Integer.sum(x, y);
                });
        int reduce3 = Stream.of(4, 5, 3, 9).reduce(0, Integer::sum);
        int max = Stream.of(4, 5, 3, 9)
                .reduce(0, (x, y) -> {
                    return x > y ? x : y;
                });
        System.out.println("max = " + max);
    }
    @Test
    public void test16(){
        // 求出所有年龄的总和
        int totalAge = Stream.of(
                        new Person("刘德华", 58),
                        new Person("张学友", 56),
                        new Person("郭富城", 54),
                        new Person("黎明", 52))
                //.map((p) -> p.getAge())
                .map(Person::getAge)
                //.reduce(0, (x, y) -> x + y);
                //.reduce(0, Integer::sum);
                .reduce(0, Integer::max);
        System.out.println("totalAge = " + totalAge);

        // 统计 数字2 出现的次数
        int count = Stream.of(1, 2, 2, 1, 3, 2)
                .map(i -> {
                    if (i == 2) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .reduce(0, Integer::sum);
        System.out.println("count = " + count);
    }
    @Test
    public void test17() {
        Stream<String> streamA = Stream.of("张三");
        Stream<String> streamB = Stream.of("李四");
        Stream<String> result = Stream.concat(streamA, streamB);
        result.forEach(System.out::println);
    }

    @Test
    public void test18() {
        IntStream intStream = Stream.of(1, 2, 3, 4, 5).mapToInt(Integer::intValue);
        intStream.filter(n -> n > 3).forEach(System.out::println);
        //intStream.filter(n -> n > 3).count;
        //intStream.filter(n -> n > 3).reduce(0, Integer::sum);
    }
    @Test
    public void test19() {
        List<String> list = Stream.of("aa", "bb", "cc").collect(Collectors.toList());
        Set<String> set = Stream.of("aa", "bb", "cc").collect(Collectors.toSet());
        ArrayList<String> arrayList = Stream.of("aa", "bb", "cc")
                .collect(Collectors.toCollection(ArrayList::new));
        HashSet<String> hashSet = Stream.of("aa", "bb", "cc")
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Test
    public void test20() {
        Stream<String> stream1 = Stream.of("aa", "bb", "cc");
        Object[] objects = stream1.toArray();
        for (Object obj : objects) {
            System.out.println();
        }
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");
        String[] strings = stream2.toArray(String[]::new);
        for (String str : strings) {
            System.out.println(str);
        }
    }

    @Test
    public void test21(){
        Stream<Student> studentStream = Stream.of(
                new Student("赵丽颖", 11, 95),
                new Student("杨颖", 12, 88),
                new Student("迪丽热巴", 12, 99),
                new Student("柳岩", 14, 77));
        // 获取最大值
        // Optional<Student> collect = studentStream.max((o1, o2) ->
        //         o1.getScore() - o2.getScore());
        // 获取最小值
        // Optional<Student> collect = studentStream.min((o1, o2) ->
        //         o1.getScore() - o2.getScore());
        // System.out.println(collect.get());
        // 求总和
        //int sumAge = studentStream.mapToInt(Student::getAge).sum();
        //System.out.println("sumAge = " + sumAge);
        // 平均值
        //double avgScore = studentStream.collect(Collectors.averagingInt(Student::getScore));
        //System.out.println("avgScore = " + avgScore);
        //OptionalDouble average = studentStream.mapToInt(Student::getScore).average();
        //if(average.isPresent()){
        //    System.out.println("sumAge = " + average.getAsDouble());
        //}
        // 统计数量
        //long count = studentStream.count();
        //System.out.println("count = " + count);
    }
    @Test
    public void test22() {
        Stream<Student> studentStream = Stream.of(
                new Student("赵丽颖", 11, 95),
                new Student("杨颖", 12, 88),
                new Student("迪丽热巴", 12, 99),
                new Student("柳岩", 14, 77));

        // 按年龄分组，key=age, value=studentList
        //Map<Integer, List<Student>> map =
        //        studentStream.collect(Collectors.groupingBy(Student::getAge));
        //map.forEach((k, v) -> {
        //    System.out.println("k = " + k + ", v = " + v);
        //});

        // 将分数大于60的分为一组,小于60分成另一组
        Map<String, List<Student>> map = studentStream.collect(Collectors.groupingBy((s) -> {
            if (s.getScore() > 80) {
                return "及格";
            } else {
                return "不及格";
            }
        }));
        map.forEach((k, v) -> {
            System.out.println(k + "::" + v);
        });

    }
    @Test
    public void test23() {
        Stream<Student> studentStream = Stream.of(
                new Student("赵丽颖", 11, 95),
                new Student("杨颖", 12, 88),
                new Student("迪丽热巴", 12, 99),
                new Student("柳岩", 14, 77));
        Map<Integer, Map<String, List<Student>>> map =
                studentStream.collect(Collectors.groupingBy(Student::getAge, Collectors.groupingBy(s -> {
                    if (s.getScore() >= 90) {
                        return "优秀";
                    } else if (s.getScore() >= 80) {
                        return "良好";
                    } else if (s.getScore() >= 70 ) {
                        return "及格";
                    } else {
                        return "不及格";
                    }
                })));
        map.forEach((k, v) -> {
            System.out.println(k + " == " + v);
        });
    }

    @Test
    public void test24() {
        Stream<Student> studentStream = Stream.of(
                new Student("赵丽颖", 11, 95),
                new Student("杨颖", 12, 88),
                new Student("迪丽热巴", 12, 99),
                new Student("柳岩", 14, 77));
        // partitioningBy会根据值是否为true，把集合分割为两个列表，一个true列表，一个false列表。
        Map<Boolean, List<Student>> collect =
                studentStream.collect(Collectors.partitioningBy(s -> s.getScore() > 80));
        collect.forEach((k, v) -> {
            System.out.println(k + "::" + v);
        });
    }
    @Test
    public void test25() {
        Stream<Student> stream = Stream.of(
                new Student("赵丽颖", 11, 95),
                new Student("杨颖", 12, 88),
                new Student("迪丽热巴", 12, 99),
                new Student("柳岩", 14, 77));
        String collect = stream.map(Student::getName).collect(Collectors.joining("-","[Start]","[End]"));
        System.out.println(collect);
    }
    @Test
    public void test26() {
        long count = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .parallel()
                .filter(s -> {
                    System.out.println(Thread.currentThread() + ", s = " + s);
                    return true;
                })
                .count();
        System.out.println("count = " + count);
    }

    /**
     *  parallelStream线程安全问题
     */
    @Test
    public void test27() throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(1);
        }
        List<Integer> newList = new ArrayList<>();
        // 使用并行的流往集合中添加数据
        list.parallelStream()
                .forEach(s -> {
                    newList.add(s);
                    //System.out.println(s);
                });
        System.out.println("newList1 = " + newList.size());
        System.out.println("newList2 = " + newList.size());
        Thread.sleep(3*1000);
        System.out.println("newList3 = " + newList.size());
    }

    @Test
    /**
     * Fork/Join案例：
     * 需求：使用Fork/Join计算1-10000的和，当一个任务的计算数量大于3000时拆分任务，数量小于3000时计算。
     */
    public void test28() {
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        SumRecursiveTask task = new SumRecursiveTask(1, 10000L);
        Long result = pool.invoke(task);
        System.out.println("result = " + result);
        long end = System.currentTimeMillis();
        System.out.println("消耗的时间为: " + (end - start));
    }

    /**
     * 测试：Optional
     */
    @Test
    public void test29() {
        // of()  与  ofNullable()  区别：
        //Optional<String> o1 = Optional.of("测试");
        //Optional<String> o1 = Optional.of(null);  // 会报错：空指针
        Optional<String> o1 = Optional.ofNullable(null);    // 空的 Optional 实例
        Optional<String> o2 = Optional.ofNullable("参数");

        // isPresent()  判断是否有值
        if (o1.isPresent()) {
            System.out.println("o1.get() = " + o1.get());
        }else{
            System.out.println("没有值");
        }

        // ifPresent() 如果存在值，则做点什么
        // 存在做的什么
        o2.ifPresent(s -> System.out.println("用户名为" + s));
        // 存在做的什么,不存在做点什么( Java 9 )
        //o2.ifPresentOrElse(s -> System.out.println("用户名为" + s)
        //        , () -> System.out.println("用户名不存在"));
    }

    @Test
    public void test30() {
        Optional<String> o1 = Optional.ofNullable(null);    // 空的 Optional 实例
        Optional<String> o2 = Optional.ofNullable("参数");
        // 若存在值则获取，不存在，则返回默认值
        // orElse()  orElseGet()
        String s1 = o1.orElse("默认值");
        System.out.println("s1 = " + s1);
        String s2 = o1.orElseGet(() -> {
            System.out.println("Supplier : Optional 没有值，正进行处理逻辑 ...");
            return "Supplier.get 的返回：值为null，已处理";
        });
        System.out.println("s2 = " + s2);
    }

}
