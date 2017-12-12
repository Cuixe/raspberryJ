package org.cuixe.raspberry;

import org.cuixe.raspberry.leds.LedsMapper;
import org.cuixe.raspberry.tasks.LedTasksManager;
import org.cuixe.raspberry.tasks.TaskManager;
import org.cuixe.raspberry.utils.Notifier;
import org.cuixe.raspberry.utils.TimeUtils;

import java.util.Properties;

public class Main {

    private static LedsMapper ledsMapper = new LedsMapper();

    public static void main(String[] args) {

        TaskManager taskManager =new LedTasksManager();
        long delay = 60*60*24;

        Notifier.info("ARGUMENTOS");
        for(int i = 0; i<args.length; i++) {
            Notifier.info(args[i]);
        }
        String initTime;
        String endTime;
        Properties properties = transformToProperties(args);
        ledsMapper.initialize();
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
        taskManager.schedulePeriodTurnOnLed(ledsMapper.getLed(9), turnOnTimeDelay, delay);
        taskManager.schedulePeriodTurnOnLed(ledsMapper.getLed(10), turnOnTimeDelay, delay);
        taskManager.schedulePeriodTurnOnLed(ledsMapper.getLed(11), turnOnTimeDelay, delay);
        taskManager.schedulePeriodTurnOnLed(ledsMapper.getLed(12), turnOnTimeDelay, delay);

        taskManager.schedulePeriodTurnOffLed(ledsMapper.getLed(9), turnOffTimeDelay, delay);
        taskManager.schedulePeriodTurnOffLed(ledsMapper.getLed(10), turnOffTimeDelay, delay);
        taskManager.schedulePeriodTurnOffLed(ledsMapper.getLed(11), turnOffTimeDelay, delay);
        taskManager.schedulePeriodTurnOffLed(ledsMapper.getLed(12), turnOffTimeDelay, delay);
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

    public static long getRemainingTime(String time) {
        String[] tokens = time.split(":");
        int hours = Integer.valueOf(tokens[0]);
        int days = hours/24;
        hours = hours % 24;
        int minutes = Integer.valueOf(tokens[1]);
        int seconds = Integer.valueOf(tokens[2]);
        return TimeUtils.getRemainingTime(days, hours, minutes, seconds);
    }
}
