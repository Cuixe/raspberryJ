package org.cuixe.raspberry.timer.events;

import org.cuixe.raspberry.utils.Notifier;

import java.util.Date;
import java.util.concurrent.Callable;

public class SingleTimerEvent extends AbstractTimerEvent {

    private String id;
    private long executionTime;
    private boolean alreadyExecuted = false;
    private Date date;
    private Callable<?> callable = () -> {return null;};

    public SingleTimerEvent(String id, long executionTime) {
        this.id = id;
        this.executionTime = executionTime;
        this.date = new Date(executionTime);
    }

    @Override
    public Date getExecutionDate() {
        return date;
    }

    @Override
    public void run() {
        try {
            callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        alreadyExecuted = true;
        Notifier.info("Event: " + id + " executed");
    }

    @Override
    public long getTimeExecution() {
        return executionTime;
    }

    @Override
    public boolean alreadyExecuted() {
        return alreadyExecuted;
    }

    @Override
    public String getIdEvent() {
        return id;
    }

    @Override
    public void setCallable(Callable<?> callable) {
        this.callable = callable;
    }
}
