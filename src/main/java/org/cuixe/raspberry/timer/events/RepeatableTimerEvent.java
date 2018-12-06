package org.cuixe.raspberry.timer.events;

import org.cuixe.raspberry.events.RepeatableEvent;

import java.util.concurrent.TimeUnit;

public class RepeatableTimerEvent extends SingleTimerEvent implements RepeatableEvent {

    private TimeUnit timeUnit = TimeUnit.HOURS;
    private long period;
    private long executions;
    private long lastExecution;

    public RepeatableTimerEvent(String id, long executionTime, long period, TimeUnit timeUnit) {
        this(id, executionTime, period);
        this.timeUnit = timeUnit;
    }

    public RepeatableTimerEvent(String id, long executionTime, long period) {
        super(id, executionTime);
        this.period = period;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public long getPeriod() {
        return period;
    }

    @Override
    public long executions() {
        return executions;
    }

    @Override
    public long getLastExecution() {
        return lastExecution;
    }

    @Override
    public void run() {
        super.run();
        executions++;
        lastExecution = System.currentTimeMillis();
    }
}
