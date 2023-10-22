package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Genius
 * @date 2023/10/22 17:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("title_scheme")
public class TitleScheme {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String system;
    private String type;
}
