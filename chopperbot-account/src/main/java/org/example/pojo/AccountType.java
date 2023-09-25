package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/24 16:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "account_type")
public class AccountType {

    private String uid;
    private List<VideoType> videoTypes;
}
