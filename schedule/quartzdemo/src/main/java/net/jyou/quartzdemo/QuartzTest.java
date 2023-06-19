package net.jyou.quartzdemo;

import lombok.SneakyThrows;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Joey Chen
 * @created 2023/3/28 19:09
 */
public class QuartzTest {
    @SneakyThrows
    public static void main(String[] args) {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(10)
                                .repeatForever()
                )
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        TimeUnit.SECONDS.sleep(60);
        scheduler.shutdown();
    }
}
