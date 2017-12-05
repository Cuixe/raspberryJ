package org.cuixe.raspberry;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<Integer, Led> leds = new HashMap<>();

    public static void main(String[] args) {
        initialize();
        Tasks.scheduleDailyToggleLed(leds.get(1), 0, 5);
        Tasks.scheduleTurnOffLed(leds.get(2), 0, 10);
        Tasks.scheduleTurnOnLed(leds.get(2), 5, 10);
    }

    private static void initialize() {
        System.out.println("INICIALIZANDO LEDS");

        for(int i =1;i<=12;i++) {
            Led led = new Led(i);
            leds.put(i, led);
            System.out.println("Led: " + led.getNumber() + " Pin: " + led.getPin().getAddress());
        }
        System.out.println("PINS Inicializados");
    }
}
