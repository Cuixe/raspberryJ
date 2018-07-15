package org.raspberryJ.client.gpio;

import org.raspberryJ.client.gpio.scheduled.Initializer;
import org.raspberryJ.client.gpio.utils.ArgumentUtils;
import org.raspberryJ.client.gpio.utils.Notifier;

import java.util.Properties;

public class ScheduledMain {

    public static void main(String args []) {

        Notifier.info("ARGUMENTOS");
        for(int i = 0; i<args.length; i++) {
            Notifier.info(args[i]);
        }
        String initTime;
        String endTime;
        Properties properties = ArgumentUtils.transformToProperties(args);
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

        new Initializer().initialize(initTime, endTime);
    }
}
