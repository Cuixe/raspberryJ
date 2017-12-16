package org.cuixe.raspberry.scheduled.tasks;

import org.cuixe.raspberry.utils.TimeUtils;

import java.util.concurrent.TimeUnit;

public abstract class ScheduledTask implements Runnable {

    private long idTask;
    private long delay = 0;
    private TimeUnit timeUnit;
    private String executionTime;
    private boolean shouldbeExecuted = false;

    public ScheduledTask(long idTask, String executionTime, TimeUnit timeUnit) {
        this.idTask = idTask;
        this.delay = TimeUtils.getRemainingTime(executionTime);
        if(delay < 0) {
            this.delay = this.delay + (60*60*24);
            this.shouldbeExecuted = true;
        }
        this.timeUnit = timeUnit;
        this.executionTime = executionTime;
    }

    public abstract void execute();

    @Override
    public void run() {
        try {
            execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getIdTask() {
        return idTask;
    }

    public long getDelay() {
        return delay;
    }


    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public abstract String getDescription();

    public boolean isShouldbeExecuted() {
        return shouldbeExecuted;
    }
}
