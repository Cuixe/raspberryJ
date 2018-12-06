package org.cuixe.raspberry.jmx.timer;

public interface JmxRepeatableTimerMBean {

    String getNextExecution();

    long getExecutions();

    String getLastExecution();

    String getTimeUnit();

    long getPeriod();
}
