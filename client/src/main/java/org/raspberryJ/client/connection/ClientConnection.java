package org.raspberryJ.client.connection;

import java.io.IOException;

public interface ClientConnection {

    int status();

    boolean isRegistered();

    void connect(String raspberryId) throws IOException;
}
