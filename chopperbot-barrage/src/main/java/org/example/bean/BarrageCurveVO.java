package org.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.core.bgevnet.bgscore.BarragePoint;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Genius
 * @date 2023/10/13 16:46
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarrageCurveVO {
    private String filePath;
    private List<BarragePoint> points;
}
