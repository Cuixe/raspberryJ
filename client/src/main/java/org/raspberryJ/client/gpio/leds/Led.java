package org.raspberryJ.client.gpio.leds;

public interface Led {

    enum State {
        TURN_ON, TURN_OFF
    };

    int getNumber();

    void turnOn();

    void turnOff();

    void toggle();

    State getState();
}
