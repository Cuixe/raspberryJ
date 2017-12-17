package org.cuixe.raspberry.scheduled;

import org.cuixe.raspberry.scheduled.tasks.ScheduledPeriodTask;
import org.cuixe.raspberry.scheduled.tasks.ScheduledTask;
import org.cuixe.raspberry.utils.Notifier;

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
