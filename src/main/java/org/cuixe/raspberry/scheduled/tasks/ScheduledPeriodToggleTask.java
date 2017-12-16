package org.cuixe.raspberry.scheduled.tasks;

import org.cuixe.raspberry.leds.Led;
import org.cuixe.raspberry.utils.Notifier;
import org.cuixe.raspberry.utils.TimeUtils;

import java.util.concurrent.TimeUnit;

public class ScheduledPeriodToggleTask extends ScheduledPeriodTask {

    private Led led;

    public ScheduledPeriodToggleTask(String executionTime, long period, TimeUnit timeUnit, Led led) {
        super(TaskIdGenerator.getIdTask(), executionTime, period, timeUnit);
        this.led = led;
    }

    @Override
    public void execute() {
        Notifier.info(" CAMBIANDO LED: " + led.getNumber());
        led.toggle();
    }

    @Override
    public String getDescription() {
        return "Toggle Led " + led.getNumber();
    }
}
