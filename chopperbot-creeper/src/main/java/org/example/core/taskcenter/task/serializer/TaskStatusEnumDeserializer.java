package org.example.core.taskcenter.task.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.core.taskcenter.task.TaskStatus;

import java.lang.reflect.Type;

/**
 * @author Genius
 * @date 2023/08/10 17:56
 **/
public class TaskStatusEnumDeserializer implements ObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        String value = defaultJSONParser.parseObject(String.class);
        for (TaskStatus status :TaskStatus.values()) {
            if (status.getName().equals(value)) {
                return (T) status;
            }
        }
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.LITERAL_STRING;
    }
}
