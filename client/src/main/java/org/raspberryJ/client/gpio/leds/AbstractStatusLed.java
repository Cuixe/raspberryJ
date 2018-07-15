package org.raspberryJ.client.gpio.leds;

public abstract class AbstractStatusLed implements Led {

    private int number;
    private State state;

    public AbstractStatusLed(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void turnOn() {
        this.state = State.TURN_ON;
    }

    @Override
    public void turnOff() {
        this.state = State.TURN_OFF;
    }

    @Override
    public void toggle() {
        state = state == State.TURN_OFF ? State.TURN_ON : State.TURN_OFF;
    }

    @Override
    public State getState() {
        return this.state;
    }
}
