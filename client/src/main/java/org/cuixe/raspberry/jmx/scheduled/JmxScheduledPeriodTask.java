package org.cuixe.raspberry.jmx.scheduled;

import org.cuixe.raspberry.scheduled.tasks.ScheduledPeriodTask;

public class JmxScheduledPeriodTask extends JmxScheduledTask implements JmxScheduledPeriodTaskMBean {

    private ScheduledPeriodTask scheduledPeriodTask;

    public JmxScheduledPeriodTask(ScheduledPeriodTask scheduledPeriodTask) {
        super(scheduledPeriodTask);
        this.scheduledPeriodTask = scheduledPeriodTask;
    }

    @Override
    public String getNextExecution() {
        return scheduledPeriodTask.getNextExecution();
    }

    @Override
    public long getPeriod() {
        return scheduledPeriodTask.getPeriod();
    }
}
