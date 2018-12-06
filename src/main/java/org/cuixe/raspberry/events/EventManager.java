package org.cuixe.raspberry.events;

public interface EventManager<T extends Event> {

    void registerEvent(T event);

    T getEvent(String event);

}
