package com.tool;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 利用 Java 8 的新时间 API 处理日期和时间
 * 默认日期格式为：yyyy-MM-dd
 */
public class DateUtil {
    @Test
    public void test() throws Exception {
        System.out.println(getTimeDiff("2018-01-02 12:00:00", "2018-01-06 12:00:00", "yyyy-MM-dd HH:mm:ss", "minute"));
    }

    /**
     * 计算两个时间的差值
     */
    public static long getTimeDiff(String time1, String time2, String format, String type) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime parseTime1 = LocalDateTime.parse(time1, formatter);
        LocalDateTime parseTime2 = LocalDateTime.parse(time2, formatter);
        Duration duration = Duration.between(parseTime1, parseTime2);
        if("day".equals(type)){
            return duration.toDays();
        }else if("hour".equals(type)){
            return duration.toHours();
        }else if("minute".equals(type)){
            return duration.toMinutes();
        }else if("second".equals(type)){
            return duration.getSeconds();
        }else {
            throw new Exception("不支持的类型");
        }
    }
    public static long getDateDiff (String date1, String date2, String format, String type) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate1 = LocalDate.parse(date1, formatter);
        LocalDate localDate2 = LocalDate.parse(date2, formatter);
        Period period = Period.between(localDate1, localDate2);
        if("year".equals(type)){
            return period.getYears();
        }else if("month".equals(type)){
            return period.getMonths();
        }else if("day".equals(type)){
            return period.getDays();
        }else {
            throw new Exception("不支持的类型");
        }
    }
    /**
     * 时间比较大小
     * time1 大于 time2 返回 1
     * 相等 返回 0
     * time1 小于 time2 返回 -1
     */
    public static int compareTime(String time1, String time2, String format) {
        if(time1.equals(time2)){
            return 0;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime parseTime1 = LocalDateTime.parse(time1, formatter);
        LocalDateTime parseTime2 = LocalDateTime.parse(time2, formatter);
        if(parseTime1.isAfter(parseTime2)){
            return 1;
        }else{
            return -1;
        }
    }
    /**
     * 与当前时间比较大小
     */
    public static int compareTime(String time, String format) {
        return compareTime(time, getNowTime(), format);
    }

    /**
     * 指定时间的基础上，加减时间
     *
     * @param time  指定时间
     * @param type  year/month/day/hour/minute
     * @param value 加减的值
     * @return
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
     * 指定日期的基础上，加减时间
     * @param time   指定日期
     * @param type   year/month/day
     * @param value  加减的值
     * @return
     */
    public static String getAddDate(String time,String format, String type, int value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate parseDate = LocalDate.parse(time, formatter);
        if ("year".equals(type)) {
            parseDate = parseDate.plusYears(value);
        } else if ("month".equals(type)) {
            parseDate = parseDate.plusMonths(value);
        } else if ("day".equals(type)) {
            parseDate = parseDate.plusDays(value);
        } else {
            return null;
        }
        return parseDate.format(formatter);
    }
    public static String getAddDate(String type, int value) {
        return getAddDate(getNowDate(), "yyyy-MM-dd", type, value);
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
