package org.cuixe.raspberry.jmx.led;

public interface JmxLedMBean {

    String getLedName();

    String getStatus();

    void turnOn();

    void turnOff();

    void toggle();

}
