package org.cuixe.raspberry.scheduled.tasks;

import org.cuixe.raspberry.utils.Notifier;
import org.cuixe.raspberry.utils.TimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public abstract class ScheduledPeriodTask extends ScheduledTask {

    private long period;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private String nextExecution;

    public ScheduledPeriodTask(long idTask, String executionTime, long period, TimeUnit timeUnit) {
        super(idTask, executionTime, timeUnit);
        this.period = period;
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plus(getDelay(), TimeUtils.cast(getTimeUnit()));
        nextExecution = format.format(localDateTime);

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
