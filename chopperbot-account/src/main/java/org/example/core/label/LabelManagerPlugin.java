package org.example.core.label;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import org.example.constpool.GlobalFileCache;
import org.example.mapper.LabelMapper;
import org.example.plugin.SpringBootPlugin;
import org.example.pojo.VideoLabel;
import org.example.sql.annotation.SQLInit;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Genius
 * @date 2023/10/23 00:10
 **/
@Component
@Data
public class LabelManagerPlugin extends SpringBootPlugin {

    @Resource
    LabelMapper mapper;

    @Override
    public boolean init() {
        return super.init();
    }

    @Override
    @SQLInit(table = "video_label",tableSQL = "CREATE TABLE \"video_label\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL,\n" +
            "\t\"label_id\"\tTEXT NOT NULL UNIQUE,\n" +
            "\t\"label\"\tTEXT NOT NULL UNIQUE,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ")",mapper = LabelMapper.class)
    public List<?> sqlInit() {
        return List.of(new VideoLabel(null, UUID.randomUUID().toString(),"搞笑"),
                new VideoLabel(null, UUID.randomUUID().toString(),"破防"),
                new VideoLabel(null, UUID.randomUUID().toString(),"泪目"),
                new VideoLabel(null, UUID.randomUUID().toString(),"精彩操作"),
                new VideoLabel(null, UUID.randomUUID().toString(),"迷惑操作"));
    }

    public List<String> labelStrList(){
        return mapper.selectList(new QueryWrapper<>()).stream().map(VideoLabel::getLabel).collect(Collectors.toList());
    }

}
