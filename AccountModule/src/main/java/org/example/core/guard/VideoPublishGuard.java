package org.example.core.guard;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.core.VideoType;
import org.example.core.exchange.Exchange;
import org.example.plugin.GuardPlugin;
import org.example.pojo.Video;
import org.example.pojo.VideoQueue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/4 22:10
 */
public class VideoPublishGuard extends GuardPlugin {

    private Exchange exchange;
    private static String PATH = "config/Account/account.json";
    private BlockingQueue<Object> receiveVideo;

    public VideoPublishGuard(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
    }


    @Override
    public boolean init() {
        exchange = new Exchange();
        JSONObject object = null;

        try {
            object = JSON.parseObject(Files.readString(Path.of(PATH)));
        } catch (IOException var16) {
            throw new RuntimeException(var16);
        }

        Iterator<Map.Entry<String, Object>> iterator = object.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)iterator.next();
            String platform = (String)entry.getKey();
            JSONObject platformData = (JSONObject)entry.getValue();
            System.out.println("Platform: " + platform);
            System.out.println("platformData: " + platformData);
            Iterator<Map.Entry<String, Object>> iterator1 = platformData.entrySet().iterator();

            while(true) {
                Object types;
                Object isMatch;
                do {
                    Map.Entry<String, Object> next = iterator1.next();
                    JSONObject value = (JSONObject)next.getValue();
                    types = value.get("type");
                    isMatch = value.get("isMatch");
                } while(types == null);

                Pattern pattern = Pattern.compile("\"([^\"]+)\"");
                Matcher matcher = pattern.matcher(types.toString());

                while(matcher.find()) {
                    String type = matcher.group(1);
                    Iterator var14 = platformData.keySet().iterator();

                    while(var14.hasNext()) {
                        String s = (String)var14.next();
                        exchange.bind(new VideoQueue(platform + "-" + s, "true".equals(isMatch)), type);
                    }
                }
            }
        }

        receiveVideo = new ArrayBlockingQueue<>(1024);
        return true;
    }

    @Override
    public void start() {
        try {
            Object videoMsg = receiveVideo.poll(5L, TimeUnit.SECONDS);
            if (videoMsg != null && videoMsg instanceof Video) {
                Video video = (Video)videoMsg;
                Iterator var3 = video.getVideoType().iterator();

                while(var3.hasNext()) {
                    VideoType videoType = (VideoType)var3.next();
                    exchange.publish(videoType.toString(), video.getMessage());
                }
            }

        } catch (InterruptedException var5) {
            throw new RuntimeException(var5);
        }
    }

    public void sendVideo(Object msg) {
        receiveVideo.offer(msg);
    }

//    public static void main(String[] args) {
//        exchange = new Exchange();
//        JSONObject object = null;
//
//        try {
//            object = JSON.parseObject(Files.readString(Path.of(PATH)));
//        } catch (IOException var16) {
//            throw new RuntimeException(var16);
//        }
//
//        Iterator<Map.Entry<String, Object>> iterator = object.entrySet().iterator();
//
//        Iterator iterator1;
//        label68:
//        while(iterator.hasNext()) {
//            Map.Entry<String, Object> entry = (Map.Entry)iterator.next();
//            String platform = (String)entry.getKey();
//            JSONObject platformData = (JSONObject)entry.getValue();
//            System.out.println("Platform: " + platform);
//            System.out.println("platformData: " + platformData);
//            iterator1 = platformData.entrySet().iterator();
//
//            while(true) {
//                Object types;
//                Object isMatch;
//                do {
//                    if (!iterator1.hasNext()) {
//                        continue label68;
//                    }
//
//                    Map.Entry<String, Object> next = (Map.Entry)iterator1.next();
//                    JSONObject value = (JSONObject)next.getValue();
//                    types = value.get("type");
//                    isMatch = value.get("isMatch");
//                } while(types == null);
//
//                Pattern pattern = Pattern.compile("\"([^\"]+)\"");
//                Matcher matcher = pattern.matcher(types.toString());
//
//                while(matcher.find()) {
//                    String type = matcher.group(1);
//                    Iterator var14 = platformData.keySet().iterator();
//
//                    while(var14.hasNext()) {
//                        String s = (String)var14.next();
//                        exchange.bind(new VideoQueue(platform + "-" + s, "true".equals(isMatch)), type);
//                    }
//                }
//            }
//        }
//
//        receiveVideo = new ArrayBlockingQueue(1024);
//
//        try {
//            Video video1 = new Video();
//            video1.setVideoType(List.of(VideoType.SAD, VideoType.HAPPY));
//            video1.setMessage("hello");
//            sendVideo(video1);
//            Object videoMsg = receiveVideo.poll(5L, TimeUnit.SECONDS);
//            if (videoMsg != null && videoMsg instanceof Video) {
//                Video video = (Video)videoMsg;
//                iterator1 = video.getVideoType().iterator();
//
//                while(iterator1.hasNext()) {
//                    VideoType videoType = (VideoType)iterator1.next();
//                    exchange.publish(videoType.toString(), video.getMessage());
//                }
//            }
//
//            System.out.println(exchange.getBindings());
//        } catch (InterruptedException var17) {
//            throw new RuntimeException(var17);
//        }
//    }
}
