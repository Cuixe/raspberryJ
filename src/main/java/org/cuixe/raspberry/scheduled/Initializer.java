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

        schedulerManager.schedulTask(new ScheduledPeriodTurnOnTask(1, initTime, SECONDS_ON_DAY, TimeUnit.SECONDS,  ledsMapper.getLed(9)));
        schedulerManager.schedulTask(new ScheduledPeriodTurnOnTask(2, initTime, SECONDS_ON_DAY, TimeUnit.SECONDS,  ledsMapper.getLed(10)));
        schedulerManager.schedulTask(new ScheduledPeriodTurnOnTask(3, initTime, SECONDS_ON_DAY, TimeUnit.SECONDS,  ledsMapper.getLed(11)));
        schedulerManager.schedulTask(new ScheduledPeriodTurnOnTask(4, initTime, SECONDS_ON_DAY, TimeUnit.SECONDS,  ledsMapper.getLed(12)));


        schedulerManager.schedulTask(new ScheduledPeriodTurnOffTask(5, endTime, SECONDS_ON_DAY, TimeUnit.SECONDS,  ledsMapper.getLed(9)));
        schedulerManager.schedulTask(new ScheduledPeriodTurnOffTask(6, endTime, SECONDS_ON_DAY, TimeUnit.SECONDS,  ledsMapper.getLed(10)));
        schedulerManager.schedulTask(new ScheduledPeriodTurnOffTask(7, endTime, SECONDS_ON_DAY, TimeUnit.SECONDS,  ledsMapper.getLed(11)));
        schedulerManager.schedulTask(new ScheduledPeriodTurnOffTask(8, endTime, SECONDS_ON_DAY, TimeUnit.SECONDS,  ledsMapper.getLed(12)));

    }
}
