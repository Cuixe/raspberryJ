package org.cuixe.raspberry.scheduled.tasks;

import java.util.concurrent.atomic.AtomicLong;

public class TaskIdGenerator {

    private static final AtomicLong id = new AtomicLong(0);

    public static long getIdTask() {
        return id.incrementAndGet();
    }
}
