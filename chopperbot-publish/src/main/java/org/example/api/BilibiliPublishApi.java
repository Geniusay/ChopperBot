package org.example.api;

import org.example.core.publisher.impl.BilibiliVideoPublisher;
import org.example.pojo.BilibiliUploader;
import org.example.pojo.BilibiliVideoManuscript;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dhx
 * @date 2023/9/18 20:42
 */
public class BilibiliPublishApi {
    public static void PublishVideo(String videoPath,String devicePath,String Cookie,String coverPath){
        List<String>list = new ArrayList<>();
        list.add("音乐");
        list.add("助眠");
        BilibiliVideoManuscript bilibiliVideoManuscript = new BilibiliVideoManuscript("test","没有简介",130,list);
        BilibiliUploader bilibiliUploader = new BilibiliUploader(Cookie,videoPath,coverPath,devicePath,bilibiliVideoManuscript);
        BilibiliVideoPublisher bilibiliVideoPublisher = new BilibiliVideoPublisher();
        bilibiliVideoPublisher.publishVideo(bilibiliUploader);
    }

    public static void main(String[] args) {
        PublishVideo(
                "C:\\Users\\admin\\Downloads\\test.mp4", //视频路径
                "C:\\Users\\admin\\Desktop\\video_bin\\", //分割后视频存放路径
                "", //b站Cookie
                "C:\\Users\\admin\\Desktop\\OIP-C.jpg");  //封面路径
    }
}
