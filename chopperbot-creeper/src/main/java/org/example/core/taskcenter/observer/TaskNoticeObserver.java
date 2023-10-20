package org.example.core.taskcenter.observer;

import org.example.constpool.ConstGroup;
import org.example.core.taskcenter.task.ReptileTask;
import org.springframework.stereotype.Component;

import java.time.temporal.ValueRange;
import java.util.List;

/**
 * @author Genius
 * @date 2023/10/20 14:35
 **/
@Component
public class TaskNoticeObserver extends AbstractTaskCenterObserver{

    private List<String> noticeList = List.of(ConstGroup.LIVE_ONLINE,ConstGroup.BARRAGE_ONLINE);

    @Override
    public void onAlready(ReptileTask task) {

    }

    @Override
    public void onRunning(ReptileTask task) {
        taskCenter.info(String.format("%s 任务爬取开始", task.getTaskId()),true);
    }

    @Override
    public void onFinish(ReptileTask task) {
        taskCenter.info(String.format("%s 任务爬取结束", task.getTaskId()),true);
    }

    @Override
    public void send() {

    }

    @Override
    public boolean isMe(String taskId) {
        return noticeList.stream().anyMatch(taskId::startsWith);
    }
}
