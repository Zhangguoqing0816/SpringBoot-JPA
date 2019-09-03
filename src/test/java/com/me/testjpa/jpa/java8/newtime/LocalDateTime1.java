package com.me.testjpa.jpa.java8.newtime;

import io.swagger.models.auth.In;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.sound.midi.Soundbank;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.Set;

/**
 * 人读的
 * LocalDate - 日期
 * LocalTime - 时间
 * LocalDateTime - 日期和时间
 *
 * 计算机读的 Instant : 时间戳（以 Unix 元年 ：1970年1月1日 00:00:00 到某个时间的时间戳）
 *
 * Duration 计算两个时间的间隔
 * Period 计算两个日期之间的间隔
 *
 * TemporalAdjuster :时间矫正器
 *
 * DateTimeFormatter 格式化时间/日期
 *
 * ZonedDate ZonedTime ZonedDateTime
 */

public class LocalDateTime1 {
    @Test
    public void test1(){
        //获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //设定日期
        LocalDateTime of = LocalDateTime.of(2015, 10, 1, 8, 22, 3);
        System.out.println(of);
        //加两天
        LocalDateTime localDateTime = now.plusDays(2);
        System.out.println(localDateTime);
        //减两个月
        LocalDateTime localDateTime1 = now.minusMonths(2);
        System.out.println(localDateTime1);
        //常用方法
        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
    }

    @Test
    public void test2(){
        //默认获取 UTC 时区 -世界协调时间,8小时偏移量
        Instant ins1 = Instant.now();
        System.out.println(ins1);
        OffsetDateTime offsetDateTime = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(ins1.toEpochMilli());//转成毫秒
    }

    //Duration 计算两时间间隔
    @Test
    public void test3() throws InterruptedException {
        Instant ins1 = Instant.now();
        Thread.sleep(3000);
        Instant ins2 = Instant.now();
        Duration between = Duration.between(ins1, ins2);
        System.out.println(between.getSeconds());//秒
        System.out.println(between.getNano());//纳秒
        System.out.println(between.toMillis());//毫秒
        System.out.println(between.toHours());//小时

        System.out.println("<------------------------------------------->");

        LocalTime lt1 = LocalTime.now();
        Thread.sleep(3000);
        LocalTime lt2 = LocalTime.now();
        Duration between1 = Duration.between(lt1, lt2);
        System.out.println(between1.getSeconds());//秒
        System.out.println(between1.getNano());//纳秒
        System.out.println(between1.toMillis());//毫秒
        System.out.println(between1.toHours());//小时

        Duration duration = between1.plusDays(2);
        System.out.println(duration.toDays());
    }

    // Period 计算两日期间隔
    @Test
    public void test4(){
        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2019, 10, 28);
        Period between = Period.between(ld1, ld2);
        System.out.println(between);
        System.out.println(between.getYears());
        System.out.println(between.getMonths());
        System.out.println(between.getDays());
    }

    // TemporalAdjuster 时间矫正器
    @Test
    public void test5() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        LocalDateTime localDateTime = ldt.withDayOfMonth(10);
        System.out.println(localDateTime);

        //下周六
        LocalDateTime with = ldt.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println(with);

        //自定义  下一个工作日
        LocalDateTime nextWorkDay = ldt.with((l) -> {
            LocalDateTime ldt2 = (LocalDateTime) l;
            DayOfWeek nowWeekDay = ldt2.getDayOfWeek();//获取周几
            if (nowWeekDay.equals(DayOfWeek.FRIDAY)) {
                return ldt2.plusDays(3);
            } else if (nowWeekDay.equals(DayOfWeek.SATURDAY)) {
                return ldt2.plusDays(2);
            } else {
                return ldt2.plusDays(1);
            }
        });
        System.out.println(nextWorkDay);
    }

    /**
     * DateTimeFormatter 时间格式化
     */
    @Test
    public void test6() {
        // 日期转字符串
        DateTimeFormatter dtf1 = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt1 = LocalDateTime.now();
        String format = ldt1.format(dtf1);
        System.out.println(format);
        System.out.println("<---------------------------->");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH/mm/ss");
        String format1 = ldt1.format(dtf2);
        String format2 = dtf2.format(ldt1);
        System.out.println(format1 + "----format2: " + format2);
        // 字符串转日期
        System.out.println("<--------------字符串转日期-------------->");
        LocalDateTime parse = ldt1.parse(format1, dtf2);
        System.out.println(parse);

        LocalDateTime parse1 = LocalDateTime.parse(format2, dtf2);
        System.out.println(parse1);
    }

    @Test
    public void test7(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();//获取时区
        System.out.println(availableZoneIds);
    }

    @Test
    public void test8() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
        System.out.println(now);
        LocalDateTime now2 = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
        ZonedDateTime zonedDateTime = now2.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);

    }



    public static void main(String[] args) throws ScriptException {
       /* LocalDateTime now = LocalDateTime.now();
        System.out.println(now);*/


      /*  System.out.println(true && (false || true));

        System.out.println(true && false || true);

        System.out.println(0 | 1 & 1 | 0);*/

       /* String str = "0 | 0 & 1";
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engineByName = sem.getEngineByName("js");
        Object eval = engineByName.eval(str);
        System.out.println(eval);*/
    }

    /**
     * 时间格式化
     */
    @Test
    public void test() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime ldt = LocalDateTime.now();
        String format = dtf.format(ldt);
        System.out.println(format);
        System.out.println("<------------------------>");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt2 = LocalDateTime.now();
        String format1 = dtf2.format(ldt2);
        System.out.println(format1);
    }
}
