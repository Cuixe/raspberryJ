package org.cuixe.raspberry.leds;

import org.cuixe.raspberry.jmx.led.JmxLedMBean;
import org.cuixe.raspberry.jmx.led.JmxLed;
import org.cuixe.raspberry.jmx.MBeanRegister;
import org.cuixe.raspberry.utils.Notifier;

import java.util.HashMap;
import java.util.Map;

public class LedsMapper {

    private Map<Integer, Led> leds = new HashMap<>();
    private static final String MBEAN_NAME = "org.cuixe.leds:name=";
    private boolean inizialized = false;

    public LedsMapper() {
        initialize();
    }

    public void initialize () {
        if (!inizialized) {
            Notifier.info("INICIALIZANDO LEDS");
            for (int i = 1; i <= 12; i++) {
                Led led = new GPIOLed(i);
                leds.put(i, led);
                Notifier.info("Inicializando Led: " + led.getNumber());
                try {
                    JmxLedMBean jmxLedMBean = new JmxLed(led);
                    MBeanRegister.registerMBean(jmxLedMBean, MBEAN_NAME + jmxLedMBean.getLedName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Notifier.info("LEDS Inicializados");
            inizialized = true;
        }

    }

    public Led getLed(int ledNumber) {
        return leds.get(ledNumber);
    }

}
