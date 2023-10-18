package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date 2023/10/16
 * @Author xiaochun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("analysis_scheme")
public class AnalysisScheme {
    @TableId(value = "id")
    private Long id;

    private String labels;

    private String system;

    private String comment;

    public String getSystem(){
        return system + " 标签：[" + labels + "]";
    }
}
