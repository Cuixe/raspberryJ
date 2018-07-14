package org.cuixe.raspberry.jmx.scheduled;

import org.cuixe.raspberry.scheduled.tasks.ScheduledTask;

public class JmxScheduledTask implements JmxScheduledTaskMBean {

    private ScheduledTask scheduledTask;

    public JmxScheduledTask(ScheduledTask scheduledTask) {
        this.scheduledTask = scheduledTask;
    }

    @Override
    public long getDelay() {
        return scheduledTask.getDelay();
    }

    @Override
    public String getUnitTimeName() {
        return scheduledTask.getTimeUnit().name();
    }

    @Override
    public boolean getWasExecuted() {
        return scheduledTask.getWasExecuted();
    }

    @Override
    public String getExecutionTime() {
        return scheduledTask.getExecutionTime();
    }
}
