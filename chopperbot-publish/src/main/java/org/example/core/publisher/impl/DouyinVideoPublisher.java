package org.example.core.publisher.impl;

import org.example.core.publisher.PlatformVideoPublisher;
import org.example.pojo.VideoToPublish;
import org.example.utils.GetScriptPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description
 * @Author welsir
 * @Date 2023/10/13 20:11
 */
public class DouyinVideoPublisher implements PlatformVideoPublisher {

    final String DOUYIN_PUBLISH_VIDEO = "org/example/core/script/douyin/DouyinVideoPublish.py";

    @Override
    public void publishVideo(VideoToPublish video) {
        try {
            List<String> command = new ArrayList<>();
            command.add("python"); // Python 解释器
            command.add(GetScriptPath.getScriptPath(DOUYIN_PUBLISH_VIDEO).toString()); // 要运行的 Python 脚本文件名
            //目前默认只有三个参数
            command.add(video.getCookies());
            command.add(video.getVideoPath());
            command.add(video.getTitle());
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process;
            process = processBuilder.start();
            int backCode = process.waitFor();
            if(backCode==0){
                System.out.println("视频发布成功!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
