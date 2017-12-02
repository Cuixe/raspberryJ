package org.cuixe.raspberry;

import com.pi4j.io.gpio.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {

    private static Map<Integer, GpioPinDigitalOutput> pins = new HashMap<>();
    private static final GpioController gpio = GpioFactory.getInstance();

    public static void main(String[] args) {
        initialize();
        int pin = 0;
        while (pin != -1) {
            Scanner lec = new Scanner(System.in);
            do {
                System.out.print("Ingrese un valor: ");
                pin = lec.nextInt();
                if (pin == -1) {
                    break;
                } else if(pin == 500) {
                    operateAll();
                } else if(!pins.containsKey(pin)) {
                    Pin raspiPin = RaspiPin.getPinByAddress(pin);
                    GpioPinDigitalOutput gpioPin = gpio.provisionDigitalOutputPin(raspiPin, "Pin" + pin, PinState.HIGH);
                    pins.put(pin, gpioPin);
                }
            } while (pin <= 0);
            opeartePin(pins.get(pin), 2);
        }
        gpio.shutdown();
    }

    private static void initialize() {
        System.out.println("Inicializando PINS");
        Pin[] allPins = RaspiPin.allPins();
        for (int i = 0; i< allPins.length; i++) {
            Pin pin = allPins[i];
            GpioPinDigitalOutput gpioPin = gpio.provisionDigitalOutputPin(pin, pin.getName(), PinState.HIGH);
            pins.put(pin.getAddress(), gpioPin);
        }
        System.out.println("PINS Inicializados");
    }

    private static void operateAll() {
        pins.values().forEach((pin) -> opeartePin(pin,1));
    }

    private static void opeartePin(GpioPinDigitalOutput pin, int timeOut) {
        try {
            System.out.println("Activando PIN: " + pin.getName());
            pin.setShutdownOptions(true, PinState.LOW);
            System.out.println("TURNING OFF");
            pin.low();
            TimeUnit.SECONDS.sleep(timeOut);
            System.out.println("TURNING ON");
            pin.high();
            TimeUnit.SECONDS.sleep(timeOut);
            pin.low();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
