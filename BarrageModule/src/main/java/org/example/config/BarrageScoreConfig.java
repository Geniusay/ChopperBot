package org.example.config;
/**
 * @description : [自定义弹幕评分配置]
 * @author : [Welsir]
 * @createTime : [2023/5/15 22:14]
 */

import com.alibaba.fastjson.JSON;
import org.example.constpool.BarrageModuleConstPool;
import org.example.core.listen.BarrageFileMonitor;
import org.example.pojo.Anchor;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author welsir
 * @date 2023/5/15 22:14
 */
public class BarrageScoreConfig {
    private static final HashMap<String,Integer> anchorScoreMap = new HashMap<>();

    /**
     * @description: Set the anchor keyword and write to the file
     */

    public static void main(String[] args) throws Exception {

//        getConfigFile();
//        ArrayList<Anchor> list = new ArrayList<>();
//        Anchor anchor = new Anchor();
//        anchor.setName("水晶哥");
//        ArrayList<Anchor.property> properties = new ArrayList<>();
//        properties.add(new Anchor.property("666",5));
//        properties.add(new Anchor.property("下播",7));
//        anchor.setProperty(properties);
//        Anchor anchor1 = new Anchor();
//        anchor1.setName("小团团");
//        ArrayList<Anchor.property> properties1 = new ArrayList<>();
//        properties1.add(new Anchor.property("666",5));
//        properties1.add(new Anchor.property("下播",7));
//        anchor1.setProperty(properties1);
//        list.add(anchor);
//        list.add(anchor1);
//        setAnchorConfig(list);
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 5));

        // 合并两个List，并去重
        list1 = Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .collect(Collectors.toList());

        // 输出去重并合并后的结果
        System.out.println("去重并合并后的结果：");
        for (Integer num : list1) {
            System.out.print(num + " ");
        }
    }
}
