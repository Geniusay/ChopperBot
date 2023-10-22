package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Genius
 * @date 2023/10/22 22:54
 **/
@Data
@TableName("video_label")
@AllArgsConstructor
@NoArgsConstructor
public class VideoLabel {
    private Integer id;
    private String label;
}
