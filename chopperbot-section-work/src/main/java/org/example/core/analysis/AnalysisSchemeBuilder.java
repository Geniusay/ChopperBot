package org.example.core.analysis;

import lombok.Data;
import org.example.bean.Barrage;
import org.example.pojo.AnalysisScheme;

import java.util.List;

/**
 * @Date 2023/10/16
 * @Author xiaochun
 */
@Data
public class AnalysisSchemeBuilder {

    private AnalysisScheme scheme;

    private List<Barrage> barrages;


    public AnalysisSchemeBuilder system(String msg){
        scheme.setSystem(msg);
        return this;
    }

    public AnalysisSchemeBuilder comment(String msg){
        scheme.setComment(msg);
        return this;
    }

    public AnalysisSchemeBuilder barrages(List<Barrage> barrages){
        this.barrages = barrages;
        return this;
    }
}
