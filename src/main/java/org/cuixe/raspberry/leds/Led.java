package org.cuixe.raspberry.leds;

public interface Led {

    int getNumber();

    void turnOn();

    void turnOff();

    void toggle();
}
