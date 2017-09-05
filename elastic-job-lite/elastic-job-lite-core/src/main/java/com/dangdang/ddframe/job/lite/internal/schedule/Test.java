package com.dangdang.ddframe.job.lite.internal.schedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import java.util.TimeZone;

public class Test
{
    private static CronTrigger createTrigger(final String cron)
    {
        String[] arr = cron.split(";");
        System.out.println(arr.length);
        if (arr.length == 2)
        {
            String newcron = arr[0];
            String timezoneID = arr[1];
            timezoneID = timezoneID.trim().isEmpty() ? "CST" : timezoneID;
            return TriggerBuilder
                    .newTrigger()
                    .withIdentity("11")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(newcron).inTimeZone(TimeZone.getTimeZone(timezoneID))
                                    .withMisfireHandlingInstructionDoNothing()).build();
        }
        return TriggerBuilder.newTrigger().withIdentity("11")
                .withSchedule(CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing()).build();
    }

    public static void main(String[] args)
    {

        String cron = "0/10 * * * * ? ;";
        System.out.println(createTrigger(cron));
    }
}
