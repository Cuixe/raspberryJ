package org.cuixe.raspberry.events;

public interface Event extends Runnable {

    long getTimeExecution();

    boolean alreadyExecuted();

    String getIdEvent();

}
