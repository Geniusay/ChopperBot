package org.example.bean.liver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bean.Liver;

/**
 * @author Genius
 * @date 2023/08/21 00:54
 **/
public class DouyuLiver extends Liver {

    private String upId;

    public DouyuLiver(String liverName, String liverId,String upId) {
        super(liverName, liverId);
        this.upId = upId;
    }
}
