package org.cuixe.raspberry.exceptions;

public class InvalidPinException extends RuntimeException {

    private int pin;

    public InvalidPinException(int pin) {
        super("Invalid pin: " + pin);
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }
}
