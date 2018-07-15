package org.raspberryJ.client.gpio.jmx.scheduled;

public interface JmxScheduledTaskMBean {

    long getDelay();

    String getUnitTimeName();

    boolean getWasExecuted();

    String getExecutionTime();

}
