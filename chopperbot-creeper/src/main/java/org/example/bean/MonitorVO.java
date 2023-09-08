package org.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/08 21:37
 **/
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitorVO {
    private String taskId;
    private String monitorType;
}
