package org.example.core.exchange;

import org.example.core.guard.VideoPushChannelGuard;
import org.example.plugin.SpringBootPlugin;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/17 15:05
 */
@Component
public class PostWorkerManager extends SpringBootPlugin {

    @Resource
    Exchange exchange;
    @Resource
    VideoPushChannelGuard videoStorehouse;

    @Override
    public boolean init() {
        return super.init();
    }

    //todo:想办法把exchange的每个不同类型管道提出来，让一个线程处理
    class PostWorker implements Runnable{

        @Override
        public void run() {

        }
    }

}
