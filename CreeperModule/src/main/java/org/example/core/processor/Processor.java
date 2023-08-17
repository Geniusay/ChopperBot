package org.example.core.processor;

import us.codecraft.webmagic.processor.PageProcessor;

public interface Processor extends PageProcessor {

    void end();
    boolean isRunning();
}
