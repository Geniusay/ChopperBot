package org.example.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Genius
 * @date 2023/09/13 19:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("liver_keyword")
public class LiverKeyword {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String liver;
    private String barrage;
    private Integer score;
    private String type;
    private Boolean isBan;


}
