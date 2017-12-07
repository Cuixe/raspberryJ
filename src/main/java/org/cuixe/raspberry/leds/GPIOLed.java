package org.cuixe.raspberry.leds;

import com.pi4j.io.gpio.*;

import java.util.HashMap;
import java.util.Map;

public class GPIOLed extends AbstractStatusLed implements Led{
    private GpioPinDigitalOutput gpioPIN;
    private State state;

    private static Map<Integer, Pin> ledPin = new HashMap<>();
    static {
        ledPin.put(1, RaspiPin.GPIO_15);
        ledPin.put(2, RaspiPin.GPIO_16);
        ledPin.put(3, RaspiPin.GPIO_01);
        ledPin.put(4, RaspiPin.GPIO_04);
        ledPin.put(5, RaspiPin.GPIO_05);
        ledPin.put(6, RaspiPin.GPIO_06);
        ledPin.put(7, RaspiPin.GPIO_10);
        ledPin.put(8, RaspiPin.GPIO_11);
        ledPin.put(9, RaspiPin.GPIO_26);
        ledPin.put(10, RaspiPin.GPIO_27);
        ledPin.put(11, RaspiPin.GPIO_28);
        ledPin.put(12, RaspiPin.GPIO_29);
    }

    public GPIOLed(int number) {
        super(number);
        gpioPIN = GPIOPort.getPin(ledPin.get(number), "LED_" + number);
        gpioPIN.setShutdownOptions(true, PinState.HIGH);
        turnOff();
    }

    public Pin getPin() {
        return ledPin.get(getNumber());
    }

    public void turnOn() {
        super.turnOn();
        gpioPIN.low();
    }

    public void turnOff() {
        super.turnOff();
        gpioPIN.high();
    }

    public void toggle() {
        super.toggle();
        gpioPIN.toggle();
    }
}
