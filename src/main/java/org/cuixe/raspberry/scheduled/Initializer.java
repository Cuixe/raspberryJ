package org.cuixe.raspberry.scheduled;

import org.cuixe.raspberry.leds.LedsMapper;
import org.cuixe.raspberry.scheduled.tasks.ScheduledPeriodTurnOffTask;
import org.cuixe.raspberry.scheduled.tasks.ScheduledPeriodTurnOnTask;

import java.util.concurrent.TimeUnit;

public class Initializer {

    private SchedulerManager schedulerManager = new SchedulerManager();
    private LedsMapper ledsMapper = new LedsMapper();
    private static final long SECONDS_ON_DAY = (60*60)*24;

    public void inicialize(String initTime, String endTime) {
        ledsMapper.initialize();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        for (int i=1;i<12;i++) {
            schedulerManager.schedulTask(new ScheduledPeriodTurnOnTask(initTime, SECONDS_ON_DAY,
                    timeUnit, ledsMapper.getLed(i)));
            schedulerManager.schedulTask(new ScheduledPeriodTurnOffTask(endTime, SECONDS_ON_DAY,
                    timeUnit,  ledsMapper.getLed(i)));
        }
    }
}
