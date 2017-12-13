package org.cuixe.raspberry.tasks;

import org.cuixe.raspberry.leds.Led;
import org.cuixe.raspberry.utils.Notifier;
import org.cuixe.raspberry.utils.TimeUtils;

public class Task implements Runnable {

    private Led led;
    private int operation;

    public Task(Led led, int operation) {
        this.led = led;
        this.operation = operation;
    }

    @Override
    public void run() {
        Notifier.info("OPERATION EN:" + System.currentTimeMillis());
        switch (operation) {
            case 1:
                Notifier.info(TimeUtils.getNowTime() +" ACTIVANDO LED: " + led.getNumber());
                led.turnOn();
                break;
            case 2:
                Notifier.info(TimeUtils.getNowTime() +" DESACTIVANDO LED: " + led.getNumber());
                led.turnOff();
                break;
            case 3:
                Notifier.info(TimeUtils.getNowTime() +" CAMBIANDO LED: " + led.getNumber());
                led.toggle();
                break;
            default:
                Notifier.info(TimeUtils.getNowTime() +" OPERACION INVALIDA: " + led.getNumber());
                    break;
        }
    }
}
