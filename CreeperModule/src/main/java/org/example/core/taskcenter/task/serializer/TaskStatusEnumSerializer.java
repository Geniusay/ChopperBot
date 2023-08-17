package org.example.core.taskcenter.task.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.core.taskcenter.task.TaskStatus;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Genius
 * @date 2023/08/10 17:53
 **/
public class TaskStatusEnumSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        TaskStatus status = (TaskStatus) o;
        jsonSerializer.out.writeString(status.getName());
    }
}
