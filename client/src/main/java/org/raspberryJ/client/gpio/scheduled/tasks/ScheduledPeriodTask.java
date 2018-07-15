package org.raspberryJ.client.gpio.scheduled.tasks;

import org.raspberryJ.client.gpio.jmx.MBeanRegister;
import org.raspberryJ.client.gpio.jmx.scheduled.JmxScheduledPeriodTask;
import org.raspberryJ.client.gpio.utils.Notifier;
import org.raspberryJ.client.gpio.utils.TimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public abstract class ScheduledPeriodTask extends ScheduledTask {

    private long period;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private String nextExecution;

    private static final String MBEAN_NAME = "org.cuixe.scheduledPeriodTask:name=";

    public ScheduledPeriodTask(String executionTime, long period, TimeUnit timeUnit) {
        super(executionTime, timeUnit);
        this.period = period;
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plus(getDelay(), TimeUtils.cast(getTimeUnit()));
        nextExecution = format.format(localDateTime);
    }

    public void registerMBean() {
        MBeanRegister.registerMBean(new JmxScheduledPeriodTask(this), MBEAN_NAME + getDescription());
    }

    @Override
    public void run() {
        execute();
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plus(period, TimeUtils.cast(getTimeUnit()));
        nextExecution = format.format(localDateTime);
        Notifier.info("Siguiente ejecucion: " + getDescription() + "a las: " + nextExecution);
    }

    public long getPeriod() {
        return period;
    }

    public String getNextExecution() {
        return nextExecution;
    }
}
