package org.example.core.taskcenter.task;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.example.core.taskcenter.task.serializer.TaskStatusEnumDeserializer;
import org.example.core.taskcenter.task.serializer.TaskStatusEnumSerializer;
import org.example.exception.taskcenter.TaskSerializationException;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * @author Genius
 * @date 2023/08/10 16:17
 **/
@Data
public class TaskRecord {
    private String taskId;
    private String startTime;
    private String endTime;
    private byte[] taskByteStream;
    @JSONField(serializeUsing = TaskStatusEnumSerializer.class, deserializeUsing = TaskStatusEnumDeserializer.class)
    private TaskStatus type;

    public <T extends ReptileTask> TaskRecord(T reptileTask){
        this.taskId = reptileTask.getTaskId();
        this.startTime = reptileTask.getStartTime();
        this.endTime = reptileTask.getEndTime();
        this.type = reptileTask.getType();
//        if(!reptileTask.getType().equals(TaskStatus.No_Status)){
//            taskByteStream = serialization(reptileTask);
//        }
    }

    public TaskRecord(String taskId, String startTime, String endTime, TaskStatus type, byte[] taskByteStream) {
        this.taskId = taskId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.taskByteStream = taskByteStream;
    }

    private <T extends ReptileTask> byte[] serialization(T reptileTask){
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(reptileTask);
            return baos.toByteArray();
        }catch (Exception e){
            throw new TaskSerializationException(reptileTask.getTaskId(),e.getMessage());
        }
    }

    public <T extends ReptileTask> T getReptileTask(){
        if(type!=TaskStatus.No_Status){
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(taskByteStream));){
                return (T) ois.readObject();
            }catch (Exception e){
                throw new TaskSerializationException(taskId,e.getMessage());
            }
        }
        return null;
    }

}
