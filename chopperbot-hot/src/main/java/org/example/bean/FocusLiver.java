package org.example.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Genius
 * @date 2023/09/09 19:57
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("focus_liver")
public class FocusLiver implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String liver;

    private String roomId;

    private String platform;

    private String tag;
}
