package org.raspberryJ.client.gpio.scheduled.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static org.cuixe.raspberryJ.gpio.utils.TimeUtils.SECONDS_PER_DAY;

public class ScheduledPeriodTaskTest extends AbstractScheduledTask {

    @Test
    public void shouldBeExecuted() {
        long nextExecution = (SECONDS_PER_DAY) - 10;
        FooPeriod scheduled = new FooPeriod(pastTime, SECONDS_PER_DAY, TimeUnit.SECONDS);
        Assert.assertTrue(scheduled.isShouldBeExecuted());
        Assert.assertEquals(scheduled.getDelay(), nextExecution);
        ZonedDateTime next = zonedNow.plusDays(1);
        next = next.minusSeconds(10);
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(next);
        Assert.assertEquals(date, scheduled.getNextExecution());
    }

    @Test
    public void shouldNotBeExecuted() {
        FooPeriod scheduled = new FooPeriod(nextTime, SECONDS_PER_DAY, TimeUnit.SECONDS);
        Assert.assertFalse(scheduled.isShouldBeExecuted());
        Assert.assertEquals(scheduled.getDelay(), 10);
        ZonedDateTime next = zonedNow.plusSeconds(10);
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(next);
        Assert.assertEquals(date, scheduled.getNextExecution());
    }

    class FooPeriod extends ScheduledPeriodTask {

        public FooPeriod(String executionTime, long period, TimeUnit timeUnit) {
            super(executionTime, period, timeUnit);
        }

        @Override
        public void execute() {

        }

        @Override
        public String getDescription() {
            return "ScheduledTask";
        }
    }

}