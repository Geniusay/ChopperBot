package org.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/06 00:35
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReptileTaskVO {
    private String taskId;
    private String startTime;
    private String endTime;
    private String status;
    private Integer hasMonitor = 0;
}
