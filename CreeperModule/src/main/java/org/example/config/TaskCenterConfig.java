package org.example.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Genius
 * @date 2023/07/29 01:26
 **/
@Data
@AllArgsConstructor
public  class TaskCenterConfig{
    private int threads;
    private int queueCapacity;
    private int waitingTime;
}
