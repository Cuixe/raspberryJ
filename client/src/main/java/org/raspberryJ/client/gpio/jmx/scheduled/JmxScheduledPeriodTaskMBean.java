package org.raspberryJ.client.gpio.jmx.scheduled;

public interface JmxScheduledPeriodTaskMBean extends JmxScheduledTaskMBean {

    String getNextExecution();

    long getPeriod();

}
