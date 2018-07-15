package org.raspberryJ.api;

public interface MessageCallback {

    void process(Message.Transport transport);

    void setConnection(Connection connection);
}
