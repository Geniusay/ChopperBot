package org.example.taskcenter.handler;

import org.example.taskcenter.request.ReptileRequest;
import org.example.taskcenter.task.ReptileTask;

public interface TaskHandler <T extends ReptileRequest>{

    ReptileTask distribute(T request);
}
