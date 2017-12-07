package org.cuixe.raspberry;

import org.cuixe.raspberry.leds.GPIOLed;
import org.cuixe.raspberry.tasks.LedTasksManager;
import org.cuixe.raspberry.tasks.TaskManager;
import org.cuixe.raspberry.utils.TimeUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main {

    private static Map<Integer, GPIOLed> leds = new HashMap<>();

    public static void main(String[] args) {

        TaskManager taskManager =new LedTasksManager();


        System.out.println("ARGUMENTOS; ");
        for(int i = 0; i<args.length; i++) {
            System.out.println(args[i]);
        }
        String initTime;
        String endTime;
        Properties properties = transformToProperties(args);
        initialize();
        if (!properties.containsKey("TURN_ON_TIME")) {
            initTime = "18:00:00";
        } else {
            initTime = properties.getProperty("TURN_ON_TIME");
        }
        if (!properties.containsKey("TURN_OFF_TIME")) {
            endTime = "25:00:00";
        } else {
            endTime = properties.getProperty("TURN_OFF_TIME");
        }


        long turnOnTimeDelay = getRemainingTime(initTime);
        long turnOffTimeDelay = getRemainingTime(endTime);
        if(turnOnTimeDelay < 0) {
            turnOnTimeDelay = 0;
        }
        if(turnOffTimeDelay < 0) {
            turnOffTimeDelay = 1;
        }
        taskManager.scheduleTurnOnLed(leds.get(9), turnOnTimeDelay);
        taskManager.scheduleTurnOnLed(leds.get(10), turnOnTimeDelay);
        taskManager.scheduleTurnOnLed(leds.get(11), turnOnTimeDelay);
        taskManager.scheduleTurnOnLed(leds.get(12), turnOnTimeDelay);

        taskManager.scheduleTurnOffLed(leds.get(9), turnOffTimeDelay);
        taskManager.scheduleTurnOffLed(leds.get(10), turnOffTimeDelay);
        taskManager.scheduleTurnOffLed(leds.get(11), turnOffTimeDelay);
        taskManager.scheduleTurnOffLed(leds.get(12), turnOffTimeDelay);
    }

    private static void initialize() {
        System.out.println("INICIALIZANDO LEDS");

        for(int i =1;i<=12;i++) {
            GPIOLed GPIOLed = new GPIOLed(i);
            leds.put(i, GPIOLed);
            System.out.println("GPIOLed: " + GPIOLed.getNumber() + " Pin: " + GPIOLed.getPin().getAddress());
        }
        System.out.println("PINS Inicializados");
    }

    private static Properties transformToProperties(String[] args) {
        Properties properties = new Properties();
        for(int i = 0 ; i< args.length; i++) {
            if(args[i].contains("=")) {
                String[] tokens = args[i].split("=");
                properties.setProperty(tokens[0], tokens[1]);
            }
        }
        return properties;
    }

    private static long getRemainingTime(String time) {
        String[] tokens = time.split(":");
        int hours = Integer.valueOf(tokens[0]);
        int days = hours/24;
        hours = hours % 24;
        int minutes = Integer.valueOf(tokens[1]);
        int seconds = Integer.valueOf(tokens[2]);
        return TimeUtils.getRemainingTime(days, hours, minutes, seconds);
    }
}
