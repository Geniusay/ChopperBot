package org.example.sectionwork;

import org.example.ConsoleApplication;
import org.example.bean.Barrage;
import org.example.core.analysis.AnalysisSchemeBuilder;
import org.example.core.analysis.EmotionAnalysisPlugin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2023/10/16
 * @Author xiaochun
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnalysisTest {

    @Resource
    EmotionAnalysisPlugin plugin;
    @Test
    public void AnalysisTest(){
        List<Barrage> barrages = new ArrayList<>();
        barrages.add(new Barrage("1", 1L, 1L,"太帅啦"));
        barrages.add(new Barrage("2", 1L, 1L,"666"));
        barrages.add(new Barrage("3", 1L, 1L,"无敌"));
        AnalysisSchemeBuilder builder = new AnalysisSchemeBuilder()
                .barrages(barrages);
        System.out.println(plugin.analysis(builder));
    }
}
