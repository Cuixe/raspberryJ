package org.cuixe.raspberry.tasks;

import org.cuixe.raspberry.leds.Led;

public class Task implements Runnable {

    private Led led;
    private int operation;

    public Task(Led led, int operation) {
        this.led = led;
        this.operation = operation;
    }

    @Override
    public void run() {
        System.out.println("OPERATION EN:" + System.currentTimeMillis());
        switch (operation) {
            case 1:
                System.out.println("ACTIVANDO LED: " + led.getNumber());
                led.turnOn();
                break;
            case 2:
                System.out.println("DESACTIVANDO LED: " + led.getNumber());
                led.turnOff();
                break;
            case 3:
                System.out.println("CAMBIANDO LED: " + led.getNumber());
                led.toggle();
                break;
            default:
                System.out.println("OPERACION INVALIDA: " + led.getNumber());
                    break;
        }
    }
}
