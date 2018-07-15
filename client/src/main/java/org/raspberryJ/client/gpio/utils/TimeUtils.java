package org.raspberryJ.client.gpio.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static final long SECONDS_PER_DAY = 60*60*24;

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

    public static TemporalUnit cast(TimeUnit timeUnit) {
        switch (timeUnit) {
            case MILLISECONDS:
                return ChronoUnit.MILLIS;
            case DAYS:
                return ChronoUnit.DAYS;
            case MINUTES:
                return ChronoUnit.MINUTES;
            case HOURS:
                return ChronoUnit.HOURS;
            case SECONDS:
                return ChronoUnit.SECONDS;
            case MICROSECONDS:
                return ChronoUnit.MICROS;
            default:
                return null;
        }
    }

    public static long getRemainingTime(String time) {
        String[] tokens = time.split(":");
        int hours = Integer.valueOf(tokens[0]);
        int days = hours/24;
        hours = hours % 24;
        int minutes = Integer.valueOf(tokens[1]);
        int seconds = Integer.valueOf(tokens[2]);
        return getRemainingTime(days, hours, minutes, seconds);
    }
}
