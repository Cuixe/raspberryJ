package org.cuixe.raspberry.scheduled.tasks;

import org.cuixe.raspberry.leds.Led;
import org.cuixe.raspberry.utils.Notifier;

import java.util.concurrent.TimeUnit;

public class ScheduledPeriodTurnOffTask extends ScheduledPeriodTask {

    private Led led;

    public ScheduledPeriodTurnOffTask(long idTask, String executionTime, long period, TimeUnit timeUnit, Led led) {
        super(idTask, executionTime, period, timeUnit);
        this.led = led;
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
