package net.jyou.javase.datetime;

import static net.jyou.util.PrintUtil.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Joey Chen
 * @created 2023/3/26 17:07
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        println("default: ", now);
        String ordinalFormat = DateTimeFormatter.ISO_ORDINAL_DATE.format(now);
        println("ordinalFormat: ", ordinalFormat);
        String basicDateFormat = DateTimeFormatter.BASIC_ISO_DATE.format(now);
        println("basicDateFormat: ", basicDateFormat);
        String isoDateTimeFormat = DateTimeFormatter.ISO_DATE_TIME.format(now);
        println("isoDateTimeFormat: ", isoDateTimeFormat);
        String yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(now);
        println("yyyyMMddHHmmss: ", yyyyMMddHHmmss);


    }
}
