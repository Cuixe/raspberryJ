package org.raspberryJ.client.gpio.scheduled.tasks;

import org.raspberryJ.client.gpio.leds.Led;
import org.raspberryJ.client.gpio.utils.Notifier;

import java.util.concurrent.TimeUnit;

public class ScheduledPeriodTurnOnTask extends ScheduledPeriodTask {

    private Led led;

    public ScheduledPeriodTurnOnTask(String executionTime, long period, TimeUnit timeUnit, Led led) {
        super(executionTime, period, timeUnit);
        this.led = led;
        this.registerMBean();
    }

    @Override
    public void execute() {
        Notifier.info(" ENCENDIENDO LED: " + led.getNumber());
        led.turnOn();
    }

    @Override
    public String getDescription() {
        return "Encendido Led " + led.getNumber();
    }

}
