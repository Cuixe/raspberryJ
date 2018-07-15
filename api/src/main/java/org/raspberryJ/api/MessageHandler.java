package org.raspberryJ.api;

public interface MessageHandler<T extends Message.Transport> {

    void process(T message);
}
