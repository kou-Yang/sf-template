package com.sf.common.base.util;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author ky
 * @description LocalDate具类
 * @date 2024-04-15 16:06
 */
public class LocalDateUtil {

    /**
     * Date 转 LocalDateTime
     *
     * @param time 时间
     * @return LocalDateTime
     */
    public LocalDateTime getLocalDateTimeOfDate(Date time) {
        return time.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Date 转 LocalDate
     *
     * @param time 时间
     * @return LocalDate
     */
    public LocalDate getLocalDateOfDate(Date time) {
        return time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 毫秒级时间戳转 LocalDateTime
     *
     * @param epochMilli 毫秒级时间戳
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeOfEpochMilli(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime 转毫秒级时间戳
     *
     * @param time 时间
     * @return 毫秒级时间戳
     */
    public static long getEpochMilliOfLocalDateTime(LocalDateTime time) {
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 毫秒级时间戳转 LocalDate
     *
     * @param epochMilli 毫秒级时间戳
     * @return LocalDate
     */
    public static LocalDate getLocalDateOfEpochMilli(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDate 转毫秒级时间戳
     *
     * @param time 时间
     * @return 毫秒级时间戳
     */
    public static long getEpochMilliOfLocalDate(LocalDate time) {
        return getEpochMilliOfLocalDateTime(LocalDateTime.of(time, LocalTime.MIN));
    }

    /**
     * 秒级时间戳转 LocalDateTime
     *
     * @param epochSecond 秒级时间戳
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeOfEpochSecond(long epochSecond) {
        return Instant.ofEpochSecond(epochSecond).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime 转秒级时间戳
     *
     * @param time 时间
     * @return 秒级时间戳
     */
    public static long getEpochSecondOfLocalDateTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    /**
     * 秒级时间戳转 LocalDate
     *
     * @param epochSecond 秒级时间戳
     * @return LocalDate
     */
    public static LocalDate getLocalDateOfEpochSecond(long epochSecond) {
        return Instant.ofEpochMilli(epochSecond).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDate 转秒级时间戳
     *
     * @param time 时间
     * @return 秒级时间戳
     */
    public static long getEpochSecondOfLocalDate(LocalDate time) {
        return getEpochSecondOfLocalDateTime(LocalDateTime.of(time, LocalTime.MIN));
    }

    /**
     * 获取 time 所在周的开始时间
     *
     * @param time 时间
     * @return time 所在周的开始时间
     */
    public static LocalDateTime getFirstDayOfWeek(LocalDateTime time) {
        int dayOfWeek = time.getDayOfWeek().getValue();
        return time.minusDays(dayOfWeek - 1).with(LocalTime.MIN);
    }

    /**
     * 获取 time 所在周的结束时间
     *
     * @param time 时间
     * @return time 所在周的结束时间
     */
    public static LocalDateTime getLastDayOfWeek(LocalDateTime time) {
        int dayOfWeek = time.getDayOfWeek().getValue();
        return time.plusDays(7 - dayOfWeek).with(LocalTime.MAX);
    }

    /**
     * 获取 time 所在月份的开始时间
     *
     * @param time 时间
     * @return time 所在月份的开始时间
     */
    public static LocalDateTime getFirstDayOfMonth(LocalDateTime time) {
        return time.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
    }

    /**
     * 获取 time 所在月份的结束时间
     *
     * @param time 时间
     * @return time 所在月份的结束时间
     */
    public static LocalDateTime getLastDayOfMonth(LocalDateTime time) {
        return time.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MIN);
    }

    /**
     * 获取 time 所在年份的开始时间
     *
     * @param time 时间
     * @return time 所在年份的开始时间
     */
    public static LocalDateTime getFirstDayOfYear(LocalDateTime time) {
        return time.with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN);
    }

    /**
     * 获取 time 所在年份的结束时间
     *
     * @param time 时间
     * @return time 所在年份的结束时间
     */
    public static LocalDateTime getLastDayOfYear(LocalDateTime time) {
        return time.with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MIN);
    }
}
