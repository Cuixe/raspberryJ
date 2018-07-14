package org.cuixe.raspberry.socket;

public interface ClientConnection {

    void startConnection() throws Exception;

    void stopConnection() throws Exception;

    int status();
}
