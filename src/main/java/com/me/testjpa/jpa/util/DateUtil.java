package com.me.testjpa.jpa.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {

    /**
     * 获取当前日期
     *
     * @return
     */
    public static LocalDate getNowLocalDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static LocalDateTime getNowLocalDateTime() {
        return LocalDateTime.now();
    }
}
