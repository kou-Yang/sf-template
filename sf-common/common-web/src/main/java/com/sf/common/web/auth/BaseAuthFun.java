package com.sf.common.web.auth;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ky
 * @description spEl表达式匹配类
 * @date 2024-05-27 16:52
 */
public class BaseAuthFun {

    public Boolean hasRole(String role) {
        return false;
    }

    public Boolean hasRoles(String... role) {
        return false;
    }

    public Boolean hasAnyRole(String... role) {
        return false;
    }

    public Boolean hasPermission(String permission) {
        return false;
    }

    public Boolean hasPermissions(String... permission) {
        return false;
    }

    public Boolean hasAnyPermission(String... permission) {
        return false;
    }

    public Boolean betweenTime(String startTime, String endTime) {
        LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime end = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(start) && now.isBefore(end);
    }
}
