package org.cuixe.raspberry.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class TimeUtils {

    private static ZoneId ZONE_ID = ZoneId.systemDefault();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss")
            .withLocale(Locale.US )
            .withZone(ZONE_ID);

    public static long castStringTime(String time) {
        String[] tokens = time.split(":");
        int hours = Integer.valueOf(tokens[0]);
        int minutes = Integer.valueOf(tokens[1]);
        int seconds = Integer.valueOf(tokens[2]);

        return (seconds * 1000) + (minutes * 60 * 1000) + (hours * 60 * 60 * 1000);
    }

    public static String formatTimeToString(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        return formatter.format(instant);
    }

    public static long getNowTime() {
        LocalTime localTime = LocalTime.now();
        return localTime.getLong(ChronoField.MILLI_OF_DAY);
    }

    public static long getToDayTime() {
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay().atZone(ZONE_ID);
        return zdt.toInstant().toEpochMilli();
    }

}
