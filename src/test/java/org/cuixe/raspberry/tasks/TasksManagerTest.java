package org.cuixe.raspberry.tasks;

import org.cuixe.raspberry.leds.FooLed;
import org.cuixe.raspberry.leds.Led;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TasksManagerTest {

    private Led led;
    private TaskManager taskManager = new LedTasksManager();

    @Before
    public void setup() {
        led = new FooLed(1);
        taskManager.setDefaultTimeUnit(TimeUnit.MILLISECONDS);
    }

    @Test
    public void toggle() throws Exception {
        led.turnOn();
        taskManager.scheduleToggleLed(led, 100);
        Assert.assertTrue(led.getState() == Led.State.TURN_ON);
        TimeUnit.MILLISECONDS.sleep(200);
        Assert.assertTrue(led.getState() == Led.State.TURN_OFF);
    }

    @Test
    public void turnOnDelay() throws Exception {
        led.turnOn();
        taskManager.scheduleTurnOffLed(led, 100);
        Assert.assertTrue(led.getState() == Led.State.TURN_ON);
        TimeUnit.MILLISECONDS.sleep(200);
        Assert.assertTrue(led.getState() == Led.State.TURN_OFF);
    }

    @Test
    public void turnOffDelay() throws Exception {
        led.turnOff();
        taskManager.scheduleTurnOnLed(led, 100);
        Assert.assertTrue(led.getState() == Led.State.TURN_OFF);
        TimeUnit.MILLISECONDS.sleep(200);
        Assert.assertTrue(led.getState() == Led.State.TURN_ON);
    }

    @Test
    public void togglePeriod() throws Exception {
        led.turnOn();
        taskManager.schedulePeriodToggleLed(led, 0, 100);
        TimeUnit.MILLISECONDS.sleep(50);
        Assert.assertTrue(led.getState() == Led.State.TURN_OFF);
        TimeUnit.MILLISECONDS.sleep(100);
        Assert.assertTrue(led.getState() == Led.State.TURN_ON);
    }

    @Test
    public void turnOffPeriodDelay() throws Exception {
        led.turnOn();
        taskManager.schedulePeriodTurnOffLed(led, 0, 100);
        TimeUnit.MILLISECONDS.sleep(100);
        Assert.assertTrue(led.getState() == Led.State.TURN_OFF);
        led.turnOn();
        TimeUnit.MILLISECONDS.sleep(200);
        Assert.assertTrue(led.getState() == Led.State.TURN_OFF);
    }

    @Test
    public void turnOnPeriodDelay() throws Exception {
        led.turnOff();
        taskManager.schedulePeriodTurnOnLed(led, 0, 100);
        TimeUnit.MILLISECONDS.sleep(100);
        Assert.assertTrue(led.getState() == Led.State.TURN_ON);
        led.turnOff();
        TimeUnit.MILLISECONDS.sleep(200);
        Assert.assertTrue(led.getState() == Led.State.TURN_ON);
    }

}