package org.raspberryJ.client.gpio.utils;

import java.util.Properties;

public class ArgumentUtils {

    public static Properties transformToProperties(String[] args) {
        Properties properties = new Properties();
        for(int i = 0 ; i< args.length; i++) {
            if(args[i].contains("=")) {
                String[] tokens = args[i].split("=");
                properties.setProperty(tokens[0], tokens[1]);
            }
        }
        return properties;
    }
}
