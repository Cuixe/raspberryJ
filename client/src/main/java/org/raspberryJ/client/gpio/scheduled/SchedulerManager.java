package org.raspberryJ.client.gpio.scheduled;

import org.raspberryJ.client.gpio.scheduled.tasks.ScheduledPeriodTask;
import org.raspberryJ.client.gpio.scheduled.tasks.ScheduledTask;
import org.raspberryJ.client.gpio.utils.Notifier;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SchedulerManager {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
    private final String ADD_TASK_MESSAGE = "AGREGANDO TAREA ";

    public void schedulTask(ScheduledPeriodTask task) {
        if(task.isShouldBeExecuted()) {
            task.execute();
        }
        scheduler.scheduleAtFixedRate(task, task.getDelay(), task.getPeriod(),task.getTimeUnit());
        Notifier.info(ADD_TASK_MESSAGE + task.getDescription() + " Siguiente Ejecucion " + task.getNextExecution());
    }

    public void schedulTask(ScheduledTask task) {
        Notifier.info(ADD_TASK_MESSAGE + task.getDescription());
        if(task.isShouldBeExecuted()) {
            task.execute();
        } else {
            scheduler.schedule(task, task.getDelay(), task.getTimeUnit());
        }
    }

}
