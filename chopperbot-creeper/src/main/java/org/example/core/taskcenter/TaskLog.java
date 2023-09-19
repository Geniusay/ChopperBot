package org.example.core.taskcenter;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.core.taskcenter.task.TaskStatus;
import org.example.core.taskcenter.task.serializer.TaskStatusEnumDeserializer;
import org.example.core.taskcenter.task.serializer.TaskStatusEnumSerializer;

import java.io.Serializable;

/**
 * @author Genius
 * @date 2023/09/10 20:00
 **/
@Data
@AllArgsConstructor
public class TaskLog implements Serializable {
    private String taskId;

    private String startTime;

    private String endTime;
    @JSONField(serializeUsing = TaskStatusEnumSerializer.class, deserializeUsing = TaskStatusEnumDeserializer.class)
    private TaskStatus type;


}
