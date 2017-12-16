package org.cuixe.raspberry.jmx;

public interface JmxLedMBean {

    String getLedName();

    String getStatus();

    void turnOn();

    void turnOff();

    void toggle();

}
