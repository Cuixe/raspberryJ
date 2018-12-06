package org.cuixe.raspberry.timer;

import org.cuixe.raspberry.events.EventManager;
import org.cuixe.raspberry.jmx.MBeanRegister;
import org.cuixe.raspberry.jmx.timer.JmxRepeatableTimer;
import org.cuixe.raspberry.timer.events.AbstractTimerEvent;
import org.cuixe.raspberry.timer.events.RepeatableTimerEvent;
import org.cuixe.raspberry.utils.Notifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class TimerEventManager implements EventManager<AbstractTimerEvent> {

    private Timer timer = new Timer("Timer");
    private Map<String, AbstractTimerEvent> events = new HashMap<>();

    private static final String MBEAN_NAME = "org.cuixe.timer.RepeatableEvent:name=";

    @Override
    public void registerEvent(AbstractTimerEvent event) {
        events.put(event.getIdEvent(), event);
        Notifier.info("Registering Event: " + event.getIdEvent());
        if(event instanceof RepeatableTimerEvent) {
            RepeatableTimerEvent repeatableEvent = (RepeatableTimerEvent)event;
            long period = repeatableEvent.getTimeUnit().toMillis(repeatableEvent.getPeriod());
            timer.scheduleAtFixedRate(repeatableEvent, repeatableEvent.getExecutionDate(), period);
            MBeanRegister.registerMBean(new JmxRepeatableTimer(repeatableEvent), MBEAN_NAME + event.getIdEvent());
        } else {
            timer.schedule(event, event.getExecutionDate());
        }
    }

    @Override
    public AbstractTimerEvent getEvent(String event) {
        return events.get(event);
    }
}
