package net.jyou.javase.datetime;


import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

import static net.jyou.util.PrintUtil.println;

/**
 * @author Joey Chen
 * @created 2023/3/26 17:08
 */
public class DurationAndPeriodDemo {
    public static void main(String[] args) {
        Duration timeToOut = Duration.ofSeconds(600);
        long minutes = timeToOut.toMinutes();
        println(timeToOut);
        println(minutes);

        Instant instant1 = Instant.ofEpochSecond(1679882000);
        Instant instant2 = Instant.ofEpochSecond(1679882600);
        Duration duration1 = Duration.between(instant1, instant2);
        Duration duration2 = Duration.between(LocalDateTime.now(),
                LocalDateTime.of(2023, 3, 27, 12, 12,12));
        println(duration1.getSeconds());
        println(duration2.getSeconds());

    }
}
