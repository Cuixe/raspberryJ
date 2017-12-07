package org.cuixe.raspberry.tasks;

import org.cuixe.raspberry.leds.Led;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LedTasksManager implements TaskManager {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
    private final String ADD_TASK_MESSAGE = "AGREGANDO TAREA %s SOBRE LED %d PRIMERA EJECUCION EN %d";
    private TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    @Override
    public void setDefaultTimeUnit(TimeUnit timeUnit){
        DEFAULT_TIME_UNIT = timeUnit;
    }

    @Override
    public void scheduleToggleLed(Led led, long delay) {
        scheduler.schedule(new Task(led, 3), delay, DEFAULT_TIME_UNIT);
    }

    @Override
    public void scheduleTurnOnLed(Led led, long delay) {
        scheduler.schedule(new Task(led, 1), delay, DEFAULT_TIME_UNIT);
    }

    @Override
    public void scheduleTurnOffLed(Led led, long delay) {
        scheduler.schedule(new Task(led, 2), delay, DEFAULT_TIME_UNIT);
    }

    @Override
    public void schedulePeriodToggleLed(Led led, long delay, long period) {
        schedulePeriodToggleLed(led, delay, period, DEFAULT_TIME_UNIT);
    }

    @Override
    public void schedulePeriodTurnOnLed(Led led, long delay, long period) {
        schedulePeriodTurnOnLed(led, delay, period, DEFAULT_TIME_UNIT);
    }

    @Override
    public void schedulePeriodTurnOffLed(Led led, long delay, long period) {
        schedulePeriodTurnOffLed(led, delay, period, DEFAULT_TIME_UNIT);
    }

    @Override
    public void schedulePeriodToggleLed(Led led, long delay, long period, TimeUnit timeUnit) {
        System.out.println(String.format(ADD_TASK_MESSAGE, "TOGGLE", led.getNumber(), delay));
        scheduler.scheduleAtFixedRate(new Task(led, 3), delay, period, timeUnit);
    }

    @Override
    public void schedulePeriodTurnOnLed(Led led, long delay, long period, TimeUnit timeUnit) {
        System.out.println(String.format(ADD_TASK_MESSAGE, "TURN_ON", led.getNumber(), delay));
        scheduler.scheduleAtFixedRate(new Task(led, 1), delay, period, timeUnit);
    }

    @Override
    public void schedulePeriodTurnOffLed(Led led, long delay, long period, TimeUnit timeUnit) {
        System.out.println(String.format(ADD_TASK_MESSAGE, "TURN_OFF", led.getNumber(), delay));
        scheduler.scheduleAtFixedRate(new Task(led, 2), delay, period, timeUnit);
    }

}
