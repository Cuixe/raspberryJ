package org.cuixe.raspberry.leds;

import org.cuixe.raspberry.utils.Notifier;

import java.util.HashMap;
import java.util.Map;

public class LedsMapper {

    private Map<Integer, Led> leds = new HashMap<>();

    public void initialize () {
        Notifier.info("INICIALIZANDO LEDS");
        for(int i =1;i<=12;i++) {
            Led led = new GPIOLed(i);
            leds.put(i, led);
            Notifier.info("Inicializando Led: " + led.getNumber());
        }
        Notifier.info("LEDS Inicializados");
    }

    public Led getLed(int ledNumber) {
        return leds.get(ledNumber);
    }

}
