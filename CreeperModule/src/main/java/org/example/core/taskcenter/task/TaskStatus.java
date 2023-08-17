package org.example.core.taskcenter.task;

/**
 * @author Genius
 * @date 2023/08/10 18:00
 **/
public enum TaskStatus{
    Already("already"),//准备就绪
    Running("running"),//正在运行
    Finish("finish"),//完成的任务
    No_Status("empty"); //没有状态，用于不需要恢复的任务
    private String name;
    TaskStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
