package org.raspberryJ.client.gpio.scheduled.tasks;

import org.raspberryJ.client.gpio.leds.Led;
import org.raspberryJ.client.gpio.utils.Notifier;

import java.util.concurrent.TimeUnit;

public class ScheduledPeriodToggleTask extends ScheduledPeriodTask {

    private Led led;

    public ScheduledPeriodToggleTask(String executionTime, long period, TimeUnit timeUnit, Led led) {
        super(executionTime, period, timeUnit);
        this.led = led;
        this.registerMBean();
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
