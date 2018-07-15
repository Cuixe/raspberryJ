package org.raspberryJ.client.gpio.jmx.led;

public interface JmxLedMBean {

    String getLedName();

    String getStatus();

    void turnOn();

    void turnOff();

    void toggle();

}
