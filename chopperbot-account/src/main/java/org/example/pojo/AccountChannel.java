package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/17 9:20
 */
@Data
@TableName(value = "account_channel")
public class AccountChannel {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "channel_id")
    private String channelId;
    @TableField(value = "account_id")
    private String accountId;

}
