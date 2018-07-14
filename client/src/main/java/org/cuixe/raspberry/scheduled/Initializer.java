package org.cuixe.raspberry.scheduled;

import org.cuixe.raspberry.leds.Led;
import org.cuixe.raspberry.leds.LedsMapper;
import org.cuixe.raspberry.scheduled.tasks.ScheduledPeriodTurnOffTask;
import org.cuixe.raspberry.scheduled.tasks.ScheduledPeriodTurnOnTask;

import java.util.concurrent.TimeUnit;

import static org.cuixe.raspberry.utils.TimeUtils.SECONDS_PER_DAY;

public class Initializer {

    private SchedulerManager schedulerManager = new SchedulerManager();
    private LedsMapper ledsMapper = new LedsMapper();

    private static final String MBEAN_NAME = "org.cuixe.scheduledPeriodTask:name=";

    public void inicialize(String initTime, String endTime) {
        ledsMapper.initialize();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        for (int i = 1;i <= 12; i++) {
            Led led = ledsMapper.getLed(i);
            if (led != null) {
                schedulerManager.schedulTask(new ScheduledPeriodTurnOnTask(initTime, SECONDS_PER_DAY,
                        timeUnit, ledsMapper.getLed(i)));
                schedulerManager.schedulTask(new ScheduledPeriodTurnOffTask(endTime, SECONDS_PER_DAY,
                        timeUnit, ledsMapper.getLed(i)));
            }
        }
    }
}
