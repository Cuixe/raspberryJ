package org.cuixe.raspberry.leds;

import com.pi4j.io.gpio.*;

public class GPIOPort {

    private static GpioController gpio = GpioFactory.getInstance();

    static GpioPinDigitalOutput getPin(Pin pin, String name) {
        return gpio.provisionDigitalOutputPin(pin, name, PinState.LOW);
    }

    public static void shutdown() {
        gpio.shutdown();
    }
}
