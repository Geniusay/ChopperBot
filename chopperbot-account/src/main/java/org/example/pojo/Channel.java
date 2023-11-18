package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/17 8:20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("account_channel")
public class Channel {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String route;

}
