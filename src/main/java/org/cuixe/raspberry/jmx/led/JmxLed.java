package org.cuixe.raspberry.jmx.led;

import org.cuixe.raspberry.leds.Led;

public class JmxLed implements JmxLedMBean {

    private Led led;

    public JmxLed(Led led) {
        this.led = led;
    }

    public String getLedName() {
        return "Led_" + led.getNumber();
    }

    public String getStatus() {
        return led.getState().name();
    }

    public void turnOn() {
        led.turnOn();
    }

    public void turnOff() {
        led.turnOff();
    }

    public void toggle() {
        led.toggle();
    }
}
