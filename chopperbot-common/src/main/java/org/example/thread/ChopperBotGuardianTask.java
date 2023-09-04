package org.example.thread;


/**
 * ChopperBot系统守护线程，用于运行一些系统启动到结束都需要使用的线程，例如：TaskCenter，HeatRecommendation，OddJobBoy等
 */
public interface ChopperBotGuardianTask {

    void threadTask();

    default void guardian(){
        ChopperBotGuardPool.GuardPool().getThreadPool().submit(
                this::threadTask
        );
    }
}
