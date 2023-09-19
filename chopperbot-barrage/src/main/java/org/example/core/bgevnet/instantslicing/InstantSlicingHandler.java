package org.example.core.bgevnet.instantslicing;

import org.example.core.taskcenter.task.ReptileTask;

public interface InstantSlicingHandler {

    void handler();

    void removeTask(String taskId);

    void addTask(ReptileTask task);

    void shutdown();
}
