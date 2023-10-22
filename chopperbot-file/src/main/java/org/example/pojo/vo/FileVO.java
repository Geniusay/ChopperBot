package org.example.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Genius
 * @date 2023/10/20 23:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
    private String fileName;
    private String fileUrl;
    private String createTime;
    private Long fileSize;
}
