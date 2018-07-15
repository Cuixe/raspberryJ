package org.raspberryJ.client.gpio.scheduled.tasks;

import org.raspberryJ.client.gpio.leds.Led;
import org.raspberryJ.client.gpio.utils.Notifier;

import java.util.concurrent.TimeUnit;

public class ScheduledPeriodTurnOffTask extends ScheduledPeriodTask {

    private Led led;

    public ScheduledPeriodTurnOffTask(String executionTime, long period, TimeUnit timeUnit, Led led) {
        super(executionTime, period, timeUnit);
        this.led = led;
        this.registerMBean();
    }

    @Override
    public void execute() {
        Notifier.info(" APAGANDO LED: " + led.getNumber());
        led.turnOff();
    }

    @Override
    public String getDescription() {
        return "Apagado Led " + led.getNumber();
    }

}
