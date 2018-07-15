package org.raspberryJ.api;

import java.io.IOException;

public interface Connection {

    void startConnection() throws IOException;

    void stopConnection() throws Exception;

    void sendMessage(Message.Transport transport) throws Exception;

    void setCallback(MessageCallback callback);

    long getConnectionId();

    int status();
}
