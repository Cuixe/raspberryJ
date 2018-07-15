package org.raspberryJ.client.gpio.jmx.led;

import org.raspberryJ.client.gpio.leds.Led;

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
