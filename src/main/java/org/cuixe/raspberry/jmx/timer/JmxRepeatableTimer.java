package org.cuixe.raspberry.jmx.timer;

import org.cuixe.raspberry.timer.events.RepeatableTimerEvent;
import org.cuixe.raspberry.utils.TimeUtils;

public class JmxRepeatableTimer implements JmxRepeatableTimerMBean {

    private RepeatableTimerEvent event;

    public JmxRepeatableTimer(RepeatableTimerEvent event) {
        this.event = event;
    }

    @Override
    public String getNextExecution() {
        long timeToNext = event.getTimeUnit().toMillis(event.getPeriod());
        long next = (event.executions() * timeToNext) + event.getTimeExecution();
        return TimeUtils.formatTimeToString(next);
    }

    @Override
    public long getExecutions() {
        return event.executions();
    }

    @Override
    public String getLastExecution() {
        return TimeUtils.formatTimeToString(event.getLastExecution());
    }

    @Override
    public String getTimeUnit() {
        return event.getTimeUnit().name();
    }

    @Override
    public long getPeriod() {
        return event.getPeriod();
    }
}
