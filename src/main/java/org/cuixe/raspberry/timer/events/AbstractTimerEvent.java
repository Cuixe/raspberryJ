package org.cuixe.raspberry.timer.events;

import org.cuixe.raspberry.events.Event;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public abstract class AbstractTimerEvent extends TimerTask implements Event {

    public abstract Date getExecutionDate();

    public abstract void setCallable(Callable<?> callable);

}
