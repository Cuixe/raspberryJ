package org.cuixe.raspberry.scheduled.tasks;

import org.cuixe.raspberry.leds.Led;
import org.cuixe.raspberry.utils.Notifier;

import java.util.concurrent.TimeUnit;

public class ScheduledPeriodTurnOnTask extends ScheduledPeriodTask {

    private Led led;

    public ScheduledPeriodTurnOnTask(String executionTime, long period, TimeUnit timeUnit, Led led) {
        super(TaskIdGenerator.getIdTask(), executionTime, period, timeUnit);
        this.led = led;
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
