package org.cuixe.raspberry.tasks;

import org.cuixe.raspberry.leds.Led;
import org.cuixe.raspberry.utils.Notifier;
import org.cuixe.raspberry.utils.TimeUtils;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LedTasksManager implements TaskManager {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
    private final String ADD_TASK_MESSAGE = "AGREGANDO TAREA %s SOBRE LED %d EJECUCION: %s";
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
        String time = getTime(delay, period, timeUnit);
        Notifier.info(String.format(ADD_TASK_MESSAGE, "TOGGLE", led.getNumber(), time));
        scheduler.scheduleAtFixedRate(new Task(led, 3), delay, period, timeUnit);
    }



    @Override
    public void schedulePeriodTurnOnLed(Led led, long delay, long period, TimeUnit timeUnit) {
        String time = getTime(delay, period, timeUnit);
        Notifier.info(String.format(ADD_TASK_MESSAGE, "TURN_ON", led.getNumber(), time));
        scheduler.scheduleAtFixedRate(new Task(led, 1), delay, period, timeUnit);
    }

    @Override
    public void schedulePeriodTurnOffLed(Led led, long delay, long period, TimeUnit timeUnit) {
        String time = getTime(delay, period, timeUnit);
        Notifier.info(String.format(ADD_TASK_MESSAGE, "TURN_OFF", led.getNumber(), time));
        scheduler.scheduleAtFixedRate(new Task(led, 2), delay, period, timeUnit);
    }

    private String getTime(long delay, long period, TimeUnit timeUnit) {
        String time = "";
        if (delay > 0) {
            time = TimeUtils.getTime(delay, cast(timeUnit));
        } else if (period > 0){
            time = TimeUtils.getTime(period, cast(timeUnit));
        }
        return time;
    }

    private TemporalUnit cast(TimeUnit timeUnit) {
        switch (timeUnit) {
            case SECONDS:
                return ChronoUnit.SECONDS;
            case HOURS:
                return ChronoUnit.HOURS;
            case MINUTES:
                return ChronoUnit.MINUTES;
            case DAYS:
                return ChronoUnit.DAYS;
            case MILLISECONDS:
                return ChronoUnit.MILLIS;
                default:
                    return null;
        }
    }

}
