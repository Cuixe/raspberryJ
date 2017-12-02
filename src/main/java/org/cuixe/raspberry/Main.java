package org.cuixe.raspberry;

import com.pi4j.io.gpio.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {
        int pin = 0;
        final GpioController gpio = GpioFactory.getInstance();
        while (pin != -1) {
            Scanner lec = new Scanner(System.in);
            do {
                System.out.print("Ingrese un valor: ");
                pin = lec.nextInt();
                if (pin == -1) {
                    break;
                }
            } while (pin <= 0);

            Pin raspiPin = RaspiPin.getPinByAddress(pin);
            final GpioPinDigitalOutput gpioPin = gpio.provisionDigitalOutputPin(raspiPin, "Pin" + pin, PinState.HIGH);
            opeartePin(gpioPin);
        }
        gpio.shutdown();

    }

    private static void opeartePin(GpioPinDigitalOutput pin) {
        try {
            System.out.println("Activando PIN: " + pin.getName());
            pin.setShutdownOptions(true, PinState.LOW);
            System.out.println("TURNING OFF");
            pin.low();
            TimeUnit.SECONDS.sleep(2);
            System.out.println("TURNING ON");
            pin.high();
            TimeUnit.SECONDS.sleep(2);
            pin.low();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
