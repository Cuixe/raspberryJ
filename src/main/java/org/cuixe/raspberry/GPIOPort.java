package org.cuixe.raspberry;

import com.pi4j.io.gpio.*;

public class GPIOPort {

    private static GpioController gpio = GpioFactory.getInstance();

    public static GpioPinDigitalOutput getPin(Pin pin, String name) {
        return gpio.provisionDigitalOutputPin(pin, name, PinState.LOW);
    }

    public static GpioPinDigitalOutput getPin(Pin pin) {
        return getPin(pin, pin.getName());
    }

    public static void shutdown() {
        gpio.shutdown();
    }
}