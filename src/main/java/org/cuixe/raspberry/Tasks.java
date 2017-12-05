package org.cuixe.raspberry;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Tasks {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
    private static long DAILY_PERIOD = 5; //60*60*24
    private static final String ADD_TASK_MESSAGE = "AGREGANDO TAREA %s SOBRE LED %d PRIMERA EJECUCION EN %d";

    public static void scheduleDailyToggleLed(Led led, long delay, long period) {

        System.out.println(String.format(ADD_TASK_MESSAGE, "TOGGLE", led.getNumber(), delay));
        scheduler.scheduleAtFixedRate(new Task(led, 3), delay, period, TimeUnit.SECONDS);
    }

    public static void scheduleTurnOnLed(Led led, long delay, long period) {
        System.out.println(String.format(ADD_TASK_MESSAGE, "TURN_ON", led.getNumber(), delay));
        scheduler.scheduleAtFixedRate(new Task(led, 1), delay, period, TimeUnit.SECONDS);
    }

    public static void scheduleTurnOffLed(Led led, long delay, long period) {
        System.out.println(String.format(ADD_TASK_MESSAGE, "TURN_OFF", led.getNumber(), delay));
        scheduler.scheduleAtFixedRate(new Task(led, 2), delay, period, TimeUnit.SECONDS);
    }

}
