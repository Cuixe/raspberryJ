package org.cuixe.raspberry.scheduled;

import org.cuixe.raspberry.events.EventManager;
import org.cuixe.raspberry.leds.Led;
import org.cuixe.raspberry.leds.LedsMapper;
import org.cuixe.raspberry.timer.TimerEventManager;
import org.cuixe.raspberry.timer.events.AbstractTimerEvent;
import org.cuixe.raspberry.timer.events.RepeatableTimerEvent;
import org.cuixe.raspberry.utils.Notifier;
import org.cuixe.raspberry.utils.TimeUtils;

import java.util.concurrent.TimeUnit;

public class Initializer {

    private LedsMapper ledsMapper = new LedsMapper();
    private EventManager<AbstractTimerEvent> eventEventManager = new TimerEventManager();

    public void registerTurnOnEvents(String stringTime) {
        long time = TimeUtils.castStringTime(stringTime);
        long executionTime = TimeUtils.getToDayTime() + time;
        for (int i = 1;i <= 12; i++) {
            Led led = ledsMapper.getLed(i);
            AbstractTimerEvent event = new RepeatableTimerEvent("TURN_ON_EVENT_" + i, executionTime, 24, TimeUnit.HOURS);
            event.setCallable(() -> {
                led.turnOn();
                Notifier.info("Turned On Led " + led.getNumber());
                return null;
            });
            eventEventManager.registerEvent(event);

        }
    }

    public void registerTurnOffEvents(String stringTime) {
        long time = TimeUtils.castStringTime(stringTime);
        long executionTime = TimeUtils.getToDayTime() + time;
        for (int i = 1;i <= 12; i++) {
            Led led = ledsMapper.getLed(i);
            RepeatableTimerEvent event = new RepeatableTimerEvent("TURN_OFF_EVENT_" + i, executionTime, 24, TimeUnit.HOURS);
            event.setCallable(() -> {
                led.turnOff();
                Notifier.info("Turned Off Led " + led.getNumber() + ", Next execution in: " +  event.getPeriod() + " " + event.getTimeUnit().name());
                return null;
            });
            eventEventManager.registerEvent(event);
        }
    }

}
