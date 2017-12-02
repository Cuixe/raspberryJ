package org.cuixe.raspberry;

import com.pi4j.io.gpio.*;
import org.cuixe.raspberry.exceptions.InvalidPinException;

import java.util.*;
import java.util.concurrent.TimeUnit;


public class Main {

    private static Map<Integer, GpioPinDigitalOutput> pins = new HashMap<>();
    private static GpioController gpio = null;
    private static List<Integer> forbiddenPins = new ArrayList<>();

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

    public static void main(String[] args) {

        int option;
        int mode;
        int led = 0;
        Scanner lec = new Scanner(System.in);
        initialize();
        while(true) {
            System.out.println("Enter operation: 1=all, 2=pin, 0=exit");
            option = lec.nextInt();
            if (option == 0) {
                break;
            } else if(option == 2) {
                System.out.println("Enter LED: 0=exit");
                led = lec.nextInt();
                if (led == 0) {
                    break;
                }
            } else if (option == 1) {
                led = -1;
            }
            System.out.println("Enter mode: 1=turnOn, 2=turnOff, 3 turnOn-turnOff, 0=exit");
            mode = lec.nextInt();
            if (mode == 0) {
                break;
            }
            work(led, mode);
        }
        gpio.shutdown();
    }

    private static void work(int led, int mode) {
        if(led != -1) {
            executeMode(led, mode);
        } else {
            ledPin.keySet().forEach((tmp) -> executeMode(tmp, mode));
        }
    }

    private static void executeMode(int led, int mode) {
        Pin pin = ledPin.get(led);
        System.out.println("Operating led: " + led + " Mode " + mode);
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
        ledPin.entrySet().forEach((entry) -> {
            entry.getKey();
            Pin pin = entry.getValue();
            GpioPinDigitalOutput gpioPin = gpio.provisionDigitalOutputPin(pin, "LED_"+entry.getKey(), PinState.HIGH);
            gpioPin.setShutdownOptions(true, PinState.LOW);
            pins.put(pin.getAddress(), gpioPin);
            System.out.println("PIN: addres:" + pin.getAddress() + " name:" +pin.getName() + " provider:" + pin.getProvider());
        });
        System.out.println("PINS Inicializados");
    }

    private static void turnOn(Pin pin) {
        GpioPinDigitalOutput gpioPin = pins.get(pin.getAddress());
        gpioPin.high();
    }

    private static void turnOff(Pin pin) {
        GpioPinDigitalOutput gpioPin = pins.get(pin.getAddress());
        gpioPin.low();
    }

    private static void operatePin(Pin pin, int timeOut) {
        GpioPinDigitalOutput gpioPin = pins.get(pin.getAddress());
        try {
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
