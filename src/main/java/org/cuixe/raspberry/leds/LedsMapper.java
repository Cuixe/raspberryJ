package org.cuixe.raspberry.leds;

import java.util.HashMap;
import java.util.Map;

public class LedsMapper {

    private Map<Integer, Led> leds = new HashMap<>();

    public void initialize () {
        System.out.println("INICIALIZANDO LEDS");
        for(int i =1;i<=12;i++) {
            Led led = new GPIOLed(i);
            leds.put(i, led);
            System.out.println("Inicializando Led: " + led.getNumber());
        }
        System.out.println("LEDS Inicializados");
    }

    public Led getLed(int ledNumber) {
        return leds.get(ledNumber);
    }

}
