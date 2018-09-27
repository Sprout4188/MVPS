package com.songcw.basecore.util;

/**
 * Created by Dingo on 16/9/27.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 文件名：DateUtils.java 日期处理相关工具类
 * 版本信息：V1.0
 * 日期：2013-03-11
 * Copyright BDVCD Corporation 2013
 * 版权所有 http://www.bdvcd.com
 */
public class DateUtil {

    /**
     * 定义常量
     **/
    public static final String DATE_JFP_STR = "yyyy年MM月";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";
    public static final String DATE_FULL_STR_NO_MIL = "yyyy-MM-dd HH:mm";
    public static final String DATE_HOUR_MINUTE = "HH:mm开始";


    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, DATE_FULL_STR);
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 两个时间比较
     *
     * @param date1
     * @return
     */
    public static int compareDateWithNow(Date date1) {
        Date date2 = new Date();
        int rnum = date1.compareTo(date2);
        return rnum;
    }

    /**
     * 两个时间比较(时间戳比较)
     *
     * @param date1
     * @return
     */
    public static int compareDateWithNow(long date1) {
        long date2 = dateToUnixTimestamp();
        if (date1 > date2) {
            return 1;
        } else if (date1 < date2) {
            return -1;
        } else {
            return 0;
        }
    }


    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        return df.format(new Date());
    }

    /**
     * 获取系统当前时间
     */
    public static String getNowTime(String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(new Date());
    }

    /**
     * 获取系统当前时间年份
     */
    public static int getNowYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取系统当前时间月份
     */
    public static int getNowMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * 获取系统当前时间日
     */
    public static int getNowDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 将【yyyy年MM月dd日】转换为【yyyy-MM-dd】
     *
     * @param oDateStr 原时间字符串
     * @return 新时间字符串
     */
    public static String transDateFormate(String oDateStr) {

        String nDateStr = "";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_SMALL_STR, Locale.CHINA);
        try {
            Date date = sdf1.parse(oDateStr);
            nDateStr = sdf2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return nDateStr;
    }

    /**
     * 获取系统当前计费期
     *
     * @return
     */
    public static String getJFPTime(long timestamp) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_JFP_STR, Locale.CHINA);
        return df.format(new Date(timestamp));
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date 需要转换的日期 yyyy-MM-dd
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将当前日期转换成Unix时间戳
     *
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp() {
        long timestamp = new Date().getTime();
        return timestamp;
    }


    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @return String 日期字符串
     */
    public static String unixTimestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR, Locale.CHINA);
        //        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    public static String unixTimestampToDate1(long timestamp) {

        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR_NO_MIL, Locale.CHINA);
        return sd.format(new Date(timestamp));
    }

    public static String getDateSimple(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_SMALL_STR, Locale.CHINA);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    public static String getDateSimpleWithMill(long timestamp) {
        timestamp = timestamp * 1000;
        SimpleDateFormat sd = new SimpleDateFormat(DATE_SMALL_STR, Locale.CHINA);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    public static String getDateHourMinute(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_HOUR_MINUTE, Locale.CHINA);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @return String 日期字符串
     */
    public static String dateToString(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR_NO_MIL);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Calendar thenCalendar = Calendar.getInstance();
        int w = thenCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        return sd.format(new Date(timestamp)) + weekDays[w];
    }

    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @return String 日期字符串
     */
    public static String dateToStringNoMil(long timestamp) {

        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR_NO_MIL, Locale.CHINA);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return sd.format(new Date(timestamp));
    }

    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @return String 日期字符串
     */
    public static String dateToStringNoMil1(long timestamp) {

        SimpleDateFormat sd = new SimpleDateFormat("yy-MM-dd HH:mm", Locale.CHINA);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return sd.format(new Date(timestamp));
    }

    /**
     * Date转换成 xxxx年xx月xx日
     */
    public static String dateToSimpleString(Date date) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(date);
    }
}