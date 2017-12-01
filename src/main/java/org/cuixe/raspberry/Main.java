package org.cuixe.raspberry;

import com.pi4j.io.gpio.*;


public class Main {

    public static void main(String[] args) {


        try {
            final GpioController gpio = GpioFactory.getInstance();

            final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14, "MyLED", PinState.HIGH);

            pin.setShutdownOptions(true, PinState.LOW);

            Thread.sleep(5000);

            // turn off gpio pin #01
            pin.low();
            System.out.println("--> GPIO state should be: OFF");

            Thread.sleep(5000);

            // toggle the current state of gpio pin #01 (should turn on)
            pin.toggle();
            System.out.println("--> GPIO state should be: ON");

            Thread.sleep(5000);

            // toggle the current state of gpio pin #01  (should turn off)
            pin.toggle();
            System.out.println("--> GPIO state should be: OFF");

            Thread.sleep(5000);

            // turn on gpio pin #01 for 1 second and then off
            System.out.println("--> GPIO state should be: ON for only 1 second");
            pin.pulse(1000, true); // set second argument to 'true' use a blocking call

            // stop all GPIO activity/threads by shutting down the GPIO controller
            // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
            gpio.shutdown();

            System.out.println("Exiting ControlGpioExample");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
