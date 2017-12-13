package org.cuixe.raspberry;

import org.cuixe.raspberry.scheduled.Initializer;
import org.cuixe.raspberry.utils.Notifier;

import java.util.Properties;

public class Main3 {

    public static void main(String args []) {

        Notifier.info("ARGUMENTOS");
        for(int i = 0; i<args.length; i++) {
            Notifier.info(args[i]);
        }
        String initTime;
        String endTime;
        Properties properties = Main.transformToProperties(args);
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

        new Initializer().inicialize(initTime, endTime);

    }
}
