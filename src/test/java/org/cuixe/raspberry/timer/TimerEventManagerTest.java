package org.cuixe.raspberry.timer;

import org.cuixe.raspberry.timer.events.RepeatableTimerEvent;
import org.cuixe.raspberry.timer.events.SingleTimerEvent;
import org.cuixe.raspberry.utils.TimeUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TimerEventManagerTest {

    TimerEventManager manager = new TimerEventManager();

    @Test
    public void registerSimplePassEvent() throws Exception {
        SingleTimerEvent event = new SingleTimerEvent("Event1" , TimeUtils.getToDayTime() + 1);
        manager.registerEvent(event);
        TimeUnit.MILLISECONDS.sleep(10);
        Assert.assertTrue(event.alreadyExecuted());

    }

    @Test
    public void registerSimpleFutureEvent() throws Exception {
        SingleTimerEvent event = new SingleTimerEvent("Event1" , System.currentTimeMillis() + 100);
        TimerEventManager manager = new TimerEventManager();
        manager.registerEvent(event);
        TimeUnit.MILLISECONDS.sleep(10);
        Assert.assertFalse(event.alreadyExecuted());
        TimeUnit.MILLISECONDS.sleep(100);
        event = (SingleTimerEvent)manager.getEvent("Event1");
        Assert.assertTrue(event.alreadyExecuted());
    }

    @Test
    public void registerRepeatableTimerEvent() throws Exception {
        long time = System.currentTimeMillis() - 100;
        RepeatableTimerEvent event = new RepeatableTimerEvent("Event1" , time, 200, TimeUnit.MILLISECONDS);
        TimerEventManager manager = new TimerEventManager();
        manager.registerEvent(event);
        TimeUnit.MILLISECONDS.sleep(10);
        Assert.assertEquals(1, event.executions());
        TimeUnit.MILLISECONDS.sleep(100);
        Assert.assertEquals(2, event.executions());
        long last = event.getLastExecution();
        TimeUnit.MILLISECONDS.sleep(100);
        Assert.assertEquals(2, event.executions());
        TimeUnit.MILLISECONDS.sleep(100);
        Assert.assertEquals(3, event.executions());
        long timePass = event.getLastExecution() - last;
        Assert.assertTrue(timePass >= (200 - 10 ) &&  timePass <= (200  + 10 ));
    }
}