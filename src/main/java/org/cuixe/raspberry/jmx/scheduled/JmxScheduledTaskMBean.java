package org.cuixe.raspberry.jmx.scheduled;

public interface JmxScheduledTaskMBean {

    long getDelay();

    String getUnitTimeName();

    boolean getWasExecuted();

    String getExecutionTime();

}
