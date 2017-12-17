package org.cuixe.raspberry.scheduled.tasks;

import org.junit.Before;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AbstractScheduledTask {

    protected String pastTime;
    protected String nextTime;
    protected ZonedDateTime zonedNow;

    @Before
    public void setup() {
        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.of("America/Los_Angeles");
        zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime minus = zonedNow.minusSeconds(10);
        ZonedDateTime plus = zonedNow.plusSeconds(10);
        pastTime = minus.getHour() + ":" + minus.getMinute()+ ":" + minus.getSecond();
        nextTime = plus.getHour() + ":" + plus.getMinute()+ ":" + plus.getSecond();
    }
}
