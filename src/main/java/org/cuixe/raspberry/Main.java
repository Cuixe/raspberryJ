package org.cuixe.raspberry;

import com.pi4j.io.gpio.*;
import org.cuixe.raspberry.exceptions.InvalidPinException;

import java.util.*;
import java.util.concurrent.TimeUnit;


public class Main {

    private static Map<Integer, GpioPinDigitalOutput> pins = new HashMap<>();
    private static GpioController gpio = null;
    private static List<Integer> forbiddenPins = new ArrayList<>();

    public static void main(String[] args) {

        int option;
        int mode;
        int pin = 0;
        Scanner lec = new Scanner(System.in);
        initialize();
        while(true) {
            System.out.println("Enter operation: 1=all, 2=pin, 0=exit");
            option = lec.nextInt();
            if (option == 0) {
                break;
            } else if(option == 2) {
                System.out.println("Enter pin: 0=exit");
                pin = lec.nextInt();
                if (pin == 0) {
                    break;
                }
            } else if (option == 1) {
                pin = -1;
            }
            System.out.println("Enter mode: 1=turnOn, 2=turnOff, 3 turnOn-turnOff, 0=exit");
            mode = lec.nextInt();
            if (mode == 0) {
                break;
            }
            work(pin, mode);
        }
        gpio.shutdown();
    }

    private static void work(int pin, int mode) {
        if(pin != -1) {
            executeMode(pin, mode);
        } else {
            pins.keySet().forEach((tmp) -> executeMode(tmp, mode));
        }
    }

    private static void executeMode(int pin, int mode) {
        System.out.println("Operating pin: " + pin + " Mode " + mode);
        try {
            if (mode == 1)
                turnOn(pin);
            else if (mode == 2)
                turnOff(pin);
            else if (mode == 3) {
                operatePin(pin, 1);
            }
        } catch (InvalidPinException ex) {
            System.out.println(ex.getMessage());
        }
    }


    private static void initialize() {
        gpio = GpioFactory.getInstance();
        forbiddenPins.add(20);
        System.out.println("Inicializando PINS");
        Pin[] allPins = RaspiPin.allPins();
        for (int i = 0; i< allPins.length; i++) {
            Pin pin = allPins[i];
            GpioPinDigitalOutput gpioPin = gpio.provisionDigitalOutputPin(pin, pin.getName(), PinState.HIGH);
            gpioPin.setShutdownOptions(true, PinState.LOW);
            pins.put(pin.getAddress(), gpioPin);
            System.out.println("PIN: addres:" + pin.getAddress() + " name:" +pin.getName() + " provider:" + pin.getProvider());
            PinMode mode = gpioPin.getMode();
            System.out.println("EXTRAS: direction: " +mode.getDirection() + " name:" + mode.getName()  + " value:" + mode.getValue());
        }
        System.out.println("PINS Inicializados");
    }

    private static void turnOn(int pin) {
        validatePin(pin);
        GpioPinDigitalOutput gpioPin = pins.get(pin);
        gpioPin.high();
    }

    private static void turnOff(int pin) {
        validatePin(pin);
        GpioPinDigitalOutput gpioPin = pins.get(pin);
        gpioPin.low();
    }

    private static void operatePin(int pin, int timeOut) {
        GpioPinDigitalOutput gpioPin = pins.get(pin);
        try {
            validatePin(pin);
            System.out.println("TURNING OFF");
            gpioPin.low();
            TimeUnit.SECONDS.sleep(timeOut);
            System.out.println("TURNING ON");
            gpioPin.high();
            TimeUnit.SECONDS.sleep(timeOut);
            gpioPin.low();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void validatePin(int pin) {
        if(forbiddenPins.contains(pin)) {
            throw new InvalidPinException(pin);
        }
    }
}
