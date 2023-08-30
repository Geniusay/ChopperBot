package org.example.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/04/26 02:07
 **/
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ConfigVO {
    private String fileName;
    private String filePath;
    private String moduleType;
}
