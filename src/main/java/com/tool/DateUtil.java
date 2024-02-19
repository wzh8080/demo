package com.tool;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * 利用 Java 8 的新时间 API 处理日期和时间
 * 默认日期格式为：yyyy-MM-dd
 */
public class DateUtil {
    @Test
    public void test(){
        System.out.println(getNowWeek());
    }

    /**
     * 指定时间的基础上，加减时间
     */
    public static String getAddTime(String time,String format, String type, int value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime parseTime = LocalDateTime.parse(time, formatter);
        if ("year".equals(type)) {
            parseTime = parseTime.plusYears(value);
        } else if ("month".equals(type)) {
            parseTime = parseTime.plusMonths(value);
        } else if ("day".equals(type)) {
            parseTime = parseTime.plusDays(value);
        } else if ("hour".equals(type)) {
            parseTime = parseTime.plusHours(value);
        } else if ("minute".equals(type)) {
            parseTime = parseTime.plusMinutes(value);
        } else {
            return null;
        }
        return parseTime.format(formatter);
    }
    public static String getAddTime(String type, int value) {
        return getAddTime(getNowTime(), "yyyy-MM-dd HH:mm:ss", type, value);
    }

    /**
     * 获取当前年份
     * @return
     */
    public static int getNowYear() {
        LocalDate nowDate = LocalDate.now();
        return nowDate.getYear();
    }
    public static int getNowMonth() {
        LocalDate nowDate = LocalDate.now();
        return nowDate.getMonthValue();
    }

    /**
     * 星期一为1，星期天为7
     * @return
     */
    public static int getNowDay() {
        LocalDate nowDate = LocalDate.now();
        return nowDate.getDayOfMonth();
    }
    public static int getNowWeek() {
        LocalDate nowDate = LocalDate.now();
        return nowDate.getDayOfWeek().getValue();
    }
    /**
     * 获取当前时间
     * @param format 时间格式
     * @return
     */
    public static String getNowTimeByFormat(String format) {
        LocalDateTime nowTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(nowTime);
    }
    public static String getNowTime() {
        return getNowTimeByFormat("yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 获取当前日期
     * @param format 日期格式
     * @return
     */
    public static String getNowDateByFormat(String format) {
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(nowDate);
    }
    public static String getNowDate() {
        return LocalDate.now().toString();
    }
}
