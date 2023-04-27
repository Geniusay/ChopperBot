package org.example.util;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Target;
import java.util.List;

/**
 * @author Genius
 * @date 2023/04/28 00:17
 **/
public class VideoUtilTest {

    //截取封面视频
    @Test
    public void generateVideoPic(){

        VideoUtil.getVideoPic(
                "E:\\Project\\ChopperBot\\SectionModule\\src\\main\\resources\\video\\76000.mp4",
                "E:\\Project\\ChopperBot\\SectionModule\\src\\main\\resources\\pic",
                "pic",
                List.of(10,20,30,40)
                );
    }

    //获取视频时长
    @Test
    public void getVideoTime(){
        System.out.println(VideoUtil.getVideoTime("E:\\Project\\ChopperBot\\SectionModule\\src\\main\\resources\\video\\76000.mp4"));
    }

    //视频转换
    @Test
    public void convertVideoType(){
        VideoUtil.videoConvert(
                "E:\\Project\\ChopperBot\\SectionModule\\src\\main\\resources\\video\\temp.m3u8",
                "E:\\Project\\ChopperBot\\SectionModule\\src\\main\\resources\\video\\",
                "temp",-1,-1,"mp4"
        );
    }

    @Test
    public void cutVideo(){
        VideoUtil.cutVideo("E:\\Project\\ChopperBot\\SectionModule\\src\\main\\resources\\video\\76000.mp4",
                "E:\\Project\\ChopperBot\\SectionModule\\src\\main\\resources\\video\\76001.mp4",10,30);
    }
}
