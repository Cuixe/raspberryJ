package org.cuixe.raspberry;

import org.cuixe.raspberry.exceptions.InvalidPinException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {

    private static Map<Integer, Led> leds = new HashMap<>();

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
    }

    private static void work(int led, int mode) {
        if(led != -1) {
            executeMode(led, mode);
        } else {
            leds.keySet().forEach((tmp) -> executeMode(tmp, mode));
        }
    }

    private static void executeMode(int ledNumber, int mode) {
        System.out.println("Operating led: " + ledNumber + " Mode " + mode);
        Led led = leds.get(ledNumber);
        try {
            if (mode == 1)
                led.turnOn();
            else if (mode == 2)
                led.turnOff();
            else if (mode == 3) {
                try {
                    led.turnOff();
                    TimeUnit.SECONDS.sleep(1);
                    led.turnOn();
                    TimeUnit.SECONDS.sleep(1);
                    led.turnOff();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (InvalidPinException ex) {
            System.out.println(ex.getMessage());
        } finally {
            GPIOPort.shutdown();
        }
    }

    private static void initialize() {
        System.out.println("Inicializando Leds");
        for(int i =1;i<=12;i++) {
            leds.put(i, new Led(i));
        }
        System.out.println("PINS Inicializados");
    }

}
