package org.example.bean.section;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Genius
 * @date 2023/10/25 17:59
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageSection extends VideoSection{
    private String title;
    private String description;
    private String coverPath;
    private String content;
    private List<String> labels;
}
