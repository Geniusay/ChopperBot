package org.example.core.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Genius
 * @date 2023/08/18 03:44
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreeperBean {
    private String name;
    private String description;
    private String author;
    private Boolean discard;
}
