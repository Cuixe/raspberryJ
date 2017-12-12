package org.cuixe.raspberry.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static long getRemainingTime(int hours, int minutes, int seconds) {
        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNext5;
        zonedNext5 = zonedNow.withHour(hours).withMinute(minutes).withSecond(seconds);
        Duration duration = Duration.between(zonedNow, zonedNext5);
        return duration.getSeconds();
    }

    public static long getRemainingTime(int days, int hours, int minutes, int seconds) {
        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNext5;
        zonedNext5 = zonedNow.withHour(hours).withMinute(minutes).withSecond(seconds);
        zonedNext5 = zonedNext5.plusDays(days);
        Duration duration = Duration.between(zonedNow, zonedNext5);
        return duration.getSeconds();
    }

    public static String getTime(long delay, TemporalUnit timeUnit) {
        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        if (delay > 0) {
            zonedNow = zonedNow.plus(delay, timeUnit);
        }
        return zonedNow.getYear()+ "/" + zonedNow.getMonth() + "/" + zonedNow.getDayOfMonth()+" " +
                zonedNow.getHour()+ ":" + zonedNow.getMinute() + ":" + zonedNow.getSecond();
    }

    public static String getNowTime() {
        LocalDateTime localDate = LocalDateTime.now();
        return localDate.getYear()+ "/" + localDate.getMonth() + "/" + localDate.getDayOfMonth()+" " +
                localDate.getHour()+ ":" + localDate.getMinute() + ":" + localDate.getSecond();
    }
}
