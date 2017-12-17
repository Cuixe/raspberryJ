package org.cuixe.raspberry.scheduled.tasks;

import org.cuixe.raspberry.jmx.MBeanRegister;
import org.cuixe.raspberry.jmx.scheduled.JmxScheduledTask;
import org.cuixe.raspberry.utils.TimeUtils;

import java.util.concurrent.TimeUnit;

public abstract class ScheduledTask implements Runnable {

    private long delay = 0;
    private TimeUnit timeUnit;
    private String executionTime;
    private boolean shouldBeExecuted = false;
    private boolean wasExecuted = false;

    private static final String MBEAN_NAME = "org.cuixe.scheduledTask:name=";

    public ScheduledTask(String executionTime, TimeUnit timeUnit) {
        this.delay = TimeUtils.getRemainingTime(executionTime);
        if(delay < 0) {
            this.delay = this.delay + (60*60*24);
            this.shouldBeExecuted = true;
        }
        this.timeUnit = timeUnit;
        this.executionTime = executionTime;
    }

    public void registerMBean() {
        MBeanRegister.registerMBean(new JmxScheduledTask(this), MBEAN_NAME + getDescription());
    }

    public abstract void execute();

    @Override
    public void run() {
        try {
            execute();
            wasExecuted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getDelay() {
        return delay;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public abstract String getDescription();

    public boolean isShouldBeExecuted() {
        return shouldBeExecuted;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public boolean getWasExecuted() {
        return wasExecuted;
    }
}
