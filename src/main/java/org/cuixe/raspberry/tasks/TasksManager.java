package org.cuixe.raspberry.tasks;

import org.cuixe.raspberry.leds.GPIOLed;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TasksManager {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
    private static long DEFAULT_PERIOD = 60*60*24;
    private static final String ADD_TASK_MESSAGE = "AGREGANDO TAREA %s SOBRE LED %d PRIMERA EJECUCION EN %d";

    public static void scheduleDailyToggleLed(GPIOLed GPIOLed, long delay) {
        scheduleDailyToggleLed(GPIOLed, delay, DEFAULT_PERIOD);
    }

    public static void scheduleTurnOnLed(GPIOLed GPIOLed, long delay) {
        scheduleTurnOnLed(GPIOLed, delay, DEFAULT_PERIOD);
    }

    public static void scheduleTurnOffLed(GPIOLed GPIOLed, long delay) {
        scheduleTurnOffLed(GPIOLed, delay, DEFAULT_PERIOD);
    }

    public static void scheduleDailyToggleLed(GPIOLed GPIOLed, long delay, long period) {
        System.out.println(String.format(ADD_TASK_MESSAGE, "TOGGLE", GPIOLed.getNumber(), delay));
        scheduler.scheduleAtFixedRate(new Task(GPIOLed, 3), delay, period, TimeUnit.SECONDS);
    }

    public static void scheduleTurnOnLed(GPIOLed GPIOLed, long delay, long period) {
        System.out.println(String.format(ADD_TASK_MESSAGE, "TURN_ON", GPIOLed.getNumber(), delay));
        scheduler.scheduleAtFixedRate(new Task(GPIOLed, 1), delay, period, TimeUnit.SECONDS);
    }

    public static void scheduleTurnOffLed(GPIOLed GPIOLed, long delay, long period) {
        System.out.println(String.format(ADD_TASK_MESSAGE, "TURN_OFF", GPIOLed.getNumber(), delay));
        scheduler.scheduleAtFixedRate(new Task(GPIOLed, 2), delay, period, TimeUnit.SECONDS);
    }

}
