package com.test.java8.date;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTest {
    /** LocalDate */
    @Test
    public void test01() {
        // 获取指定日期
        LocalDate fj = LocalDate.of(1985, 9, 23);
        System.out.println("指定日期 = " + fj); // 1985-09-23
        // 获取当前日期
        LocalDate nowDate = LocalDate.now();
        System.out.println("当前日期 = " + nowDate); // 2024-02-08
        // 获取日期信息
        System.out.println("年: " + nowDate.getYear());  // 2024
        System.out.println("月: " + nowDate.getMonthValue());    // 2
        System.out.println("日: " + nowDate.getDayOfMonth());    // 8
        System.out.println("星期: " + nowDate.getDayOfWeek());   // THURSDAY
    }
    /** LocalTime */
    @Test
    public void test02() {
        // 得到指定的时间
        LocalTime time = LocalTime.of(12,15, 28, 123456789);
        System.out.println("指定时间 time = " + time);
        // 得到当前时间
        LocalTime nowTime = LocalTime.now();
        System.out.println("当前时间 nowTime = " + nowTime);
        // 获取时间信息
        System.out.println("小时: " + nowTime.getHour());
        System.out.println("分钟: " + nowTime.getMinute());
        System.out.println("秒: " + nowTime.getSecond());
        System.out.println("纳秒: " + nowTime.getNano());
    }

    /** LocalDateTime 格式为 2018-09-06T15:33:56.750 */
    @Test
    public void test03(){
        LocalDateTime fj = LocalDateTime.of(1985, 9, 23, 9, 10, 20);
        System.out.println("指定日期 = " + fj); // 1985-09-23T09:10:20
        // 得到当前日期时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前日期时间 = " + now); // 2019-10-16T16:42:24.497896800
        System.out.println("当前日期时间 年 = " + now.getYear());
        System.out.println("当前日期时间 月 = " + now.getMonthValue());
        System.out.println("当前日期时间 日 = " + now.getDayOfMonth());
        System.out.println("当前日期时间 时 = " + now.getHour());
        System.out.println("当前日期时间 分 = " + now.getMinute());
        System.out.println("当前日期时间 秒 = " + now.getSecond());
        System.out.println("当前日期时间 纳秒 = " + now.getNano());
    }

    /** 修改时间 */
    @Test
    public void test04() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前日期时间 = " + now);

        // 修改日期时间
        LocalDateTime alterDate1 = now.withHour(10).withMinute(10).withSecond(10).withNano(100000000);
        System.out.println("当前日期时间 = " + now);
        System.out.println("修改日期时间 = " + alterDate1);

        // 注：当加减时间时，若同时加/减“分钟”“秒”时，“秒”跨了60，分钟则会随之加/减1分钟
        // 在当前对象的基础上加上指定的时间
        LocalDateTime alterDate2 = now.plusHours(10).plusMinutes(10).plusSeconds(10).plusNanos(100000000);
        System.out.println("当前日期时间 = " + now);
        System.out.println("加上指定的时间 = " + alterDate2);
        // 在当前对象的基础上减去指定的时间
        LocalDateTime alterDate3 = now.minusHours(10).minusMinutes(10).minusSeconds(10).minusNanos(100000000);
        System.out.println("当前日期时间 = " + now);
        System.out.println("减去指定的时间 = " + alterDate3);
    }

    /** 日期时间的比较 */
    @Test
    public void test05() {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2018, 8, 8);
        System.out.println(now.isBefore(date)); // false
        System.out.println(now.isAfter(date)); // true

        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime date1 = LocalDateTime.of(2018, 8, 8, 10, 10, 10, 100000000);
        System.out.println("now1.isAfter(date) = " + now1.isAfter(date1));
    }

    /** 日期时间的格式化 */
    @Test
    public void test06() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前日期时间 = " + now);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("当前日期时间 = " + formatter.format(now));
        // 将字符串解析为日期时间
        LocalDateTime parse = LocalDateTime.parse("1985/09/23 10:12:22", formatter);
        System.out.println("parse = " + parse);
    }

    // 时间戳
    @Test
    public void test07() {
        Instant now = Instant.now();
        System.out.println("当前时间戳 = " + now);
        // 获取从1970年1月1日 00:00:00的秒
        System.out.println(now.getNano());
        System.out.println(now.getEpochSecond());
        System.out.println(now.toEpochMilli());
        System.out.println("System.currentTimeMillis() = \n" + System.currentTimeMillis());
        Instant instant = Instant.ofEpochSecond(5);
        System.out.println(instant);
    }
}
