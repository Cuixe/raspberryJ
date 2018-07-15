package org.raspberryJ.server.connection;

public interface GpioController {

    void operatePin(int pin, boolean activate);
}
