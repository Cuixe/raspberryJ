package org.cuixe.raspberry.events;

import java.util.concurrent.TimeUnit;

public interface RepeatableEvent extends Event {

    TimeUnit getTimeUnit();

    long getPeriod();

    long executions();

    long getLastExecution();
}
