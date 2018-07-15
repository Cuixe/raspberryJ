package org.raspberryJ.client.gpio.scheduled.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.cuixe.raspberryJ.gpio.utils.TimeUtils.SECONDS_PER_DAY;

public class ScheduledTaskTest extends AbstractScheduledTask {

    @Test
    public void shouldBeExecuted() {
        FooScheduled scheduled = new FooScheduled(pastTime, TimeUnit.SECONDS);
        Assert.assertTrue(scheduled.isShouldBeExecuted());
        Assert.assertEquals(scheduled.getDelay(), SECONDS_PER_DAY - 10);
    }

    @Test
    public void shouldNotBeExecuted() {
        FooScheduled scheduled = new FooScheduled(nextTime, TimeUnit.SECONDS);
        Assert.assertFalse(scheduled.isShouldBeExecuted());
        Assert.assertEquals(scheduled.getDelay(), 10);
    }

    class FooScheduled extends ScheduledTask {

        public FooScheduled(String executionTime, TimeUnit timeUnit) {
            super(executionTime, timeUnit);
        }

        @Override
        public void execute() {

        }

        @Override
        public String getDescription() {
            return "Scheduled";
        }
    }

}