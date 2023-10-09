package org.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Genius
 * @date 2023/10/09 16:03
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuardVO {
    private String guardName;
    private long delayTime;
    private int failRetryTimes;
    private LocalDateTime preGuardTime;
}
