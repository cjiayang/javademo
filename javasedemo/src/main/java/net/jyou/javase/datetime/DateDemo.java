package net.jyou.javase.datetime;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static net.jyou.util.PrintUtil.println;

/**
 * @author Joey Chen
 * @created 2023/3/26 17:07
 */
public class DateDemo {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        println(now);

        LocalDate date1 = LocalDate.of(2021, 9, 12);
        LocalDate date2 = LocalDate.of(2021, Month.AUGUST, 25);
        LocalDate yearDay = LocalDate.ofYearDay(2021, 100);
        println(date1);
        println(date2);
        println(yearDay);

        LocalDate parseDate = LocalDate.parse("2021-08-25");
        println(parseDate);

        LocalDateTime atTime1 = now.atTime(8, 25);
        LocalDateTime atTime2 = now.atTime(8, 25, 22, 22);
        LocalDateTime nowTime = now.atTime(LocalTime.now());
        println(atTime1);
        println(atTime2);
        println(nowTime);
    }
}
