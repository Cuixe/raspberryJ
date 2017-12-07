package org.cuixe.raspberry.tasks;

import org.cuixe.raspberry.leds.Led;

import java.util.concurrent.TimeUnit;

public interface TaskManager {

    void setDefaultTimeUnit(TimeUnit timeUnit);

    void scheduleToggleLed(Led Led, long delay);

    void scheduleTurnOnLed(Led Led, long delay);

    void scheduleTurnOffLed(Led Led, long delay);

    void schedulePeriodToggleLed(Led Led, long delay, long period);

    void schedulePeriodTurnOnLed(Led Led, long delay, long period);

    void schedulePeriodTurnOffLed(Led Led, long delay, long period);

    void schedulePeriodToggleLed(Led Led, long delay, long period, TimeUnit timeUnit);

    void schedulePeriodTurnOnLed(Led Led, long delay, long period, TimeUnit timeUnit);

    void schedulePeriodTurnOffLed(Led Led, long delay, long period, TimeUnit timeUnit);
}
