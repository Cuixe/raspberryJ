package org.cuixe.raspberry;

import org.cuixe.raspberry.exceptions.InvalidPinException;
import org.cuixe.raspberry.leds.GPIOLed;
import org.cuixe.raspberry.leds.GPIOPort;
import org.cuixe.raspberry.leds.Led;
import org.cuixe.raspberry.leds.LedsMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class CliMain {

    private static LedsMapper ledsMapper = new LedsMapper();

    public static void main(String[] args) {
        int mode;
        int led = 0;
        Scanner lec = new Scanner(System.in);
        ledsMapper.initialize();
        while(true) {
            System.out.println("Enter LED: (0 = todos, -1=exit)");
            led = lec.nextInt();
            if (led == -1) {
                break;
            }

            System.out.println("Enter mode: 1=turnOn, 2=turnOff, 3 turnOn-turnOff, -1=exit");
            mode = lec.nextInt();
            if (mode == -1) {
                break;
            }
            work(led, mode);
        }
    }

    private static void work(int led, int mode) {
        if(led != 0) {
            executeMode(led, mode);
        } else {
            System.out.println("Operating all leds");
            for(int i = 1; i<= 12; i++) {
                executeMode(i, mode);
            }
        }
    }

    private static void executeMode(int ledNumber, int mode) {
        System.out.println("Operating GPIOLed: " + ledNumber + " Mode " + mode);
        Led led = ledsMapper.getLed(ledNumber);
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
        }
    }

}
