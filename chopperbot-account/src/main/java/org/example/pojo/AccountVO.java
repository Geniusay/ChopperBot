package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/12 21:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {
    private Long uid;
    private String username;
    private String platform;
    List<AccountType> typeList;
}
