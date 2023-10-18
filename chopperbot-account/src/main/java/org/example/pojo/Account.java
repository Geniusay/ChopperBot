package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/4 22:08
 */
@TableName(value = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

    @TableId(value = "uid", type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String cookies;
    private boolean is_complete_match;
    private int platform_id;

}
