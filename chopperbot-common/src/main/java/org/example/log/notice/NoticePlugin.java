package org.example.log.notice;

import lombok.NoArgsConstructor;
import org.example.plugin.SpringBootPlugin;
import org.example.plugin.SpringGuardPlugin;
import org.example.plugin.annotation.CheckPlugin;
import org.example.thread.oddjob.OddJobBoy;
import org.example.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Genius
 * @date 2023/10/19 16:00
 **/
@Component
@NoArgsConstructor
public class NoticePlugin extends SpringBootPlugin {

    @Resource
    private NoticeHandler handler;

    private ExecutorService pool;

    @Override
    public boolean init() {
        if(handler==null){
            throw new RuntimeException("Notice Handler is null");
        }
        pool = Executors.newSingleThreadExecutor();
        return super.init();
    }

    public void notice(Notice notice) {
        pool.submit(()->{
            handler.doHandler(notice);
        });
    }

    public void notice(NoticeType type,String form,String content){
        notice(new Notice(type,form,content));
    }

    public void notice(String type,String form,String content){
        notice(new Notice(type,form,content));
    }

}
