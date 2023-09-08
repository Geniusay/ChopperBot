package org.example.core.taskmonitor;

import java.io.Serializable;

public interface TaskMonitor extends Serializable {

    void monitor();

    boolean isOpen();

    boolean stop();

    boolean close();
}
