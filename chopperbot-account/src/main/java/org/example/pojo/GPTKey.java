package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date 2023/10/12
 * @Author xiaochun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("gpt_key")
public class GPTKey {
    private String key;

    private String url;

    private String model;

    private String function;
}
