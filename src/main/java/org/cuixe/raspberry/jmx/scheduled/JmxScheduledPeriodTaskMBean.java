package org.cuixe.raspberry.jmx.scheduled;

public interface JmxScheduledPeriodTaskMBean extends JmxScheduledTaskMBean {

    String getNextExecution();

    long getPeriod();

}
