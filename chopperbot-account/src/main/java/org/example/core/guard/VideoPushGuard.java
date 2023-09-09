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
public class VideoPushGuard extends GuardPlugin {

    private static Exchange exchange;
    private static String PATH = "config/Account/account.json";
    private static BlockingQueue<Object> receiveVideo;

    public VideoPushGuard(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
    }


    @Override
    public boolean init() {
//        exchange = new Exchange();
//        try {
//            String jsonData = Files.readString(Path.of(PATH));
//            JSONObject jsonObject = JSON.parseObject(jsonData);
//
//            for (Map.Entry<String, Object> platformEntry : jsonObject.entrySet()) {
//                String platform = platformEntry.getKey();
//                JSONObject platformData = (JSONObject) platformEntry.getValue();
//
//                System.out.println("Platform: " + platform);
//                System.out.println("platformData: " + platformData);
//
//                String types = getStringValueOrDefault(platformData, "type", "");
//                boolean isMatch = getBooleanValueOrDefault(platformData, "isMatch", false);
//
//                Pattern pattern = Pattern.compile("\"([^\"]+)\"");
//                Matcher matcher = pattern.matcher(types);
//
//                while (matcher.find()) {
//                    String type = matcher.group(1);
//                    for (String queueName : platformData.keySet()) {
//                        exchange.bind(new VideoQueue(platform + "-" + queueName, isMatch), type);
//                    }
//                }
//            }
//
//            receiveVideo = new ArrayBlockingQueue<>(1024);
            return true;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void start() {
//        try {
//            Object videoMsg = receiveVideo.poll(5, TimeUnit.SECONDS);
//            if (videoMsg instanceof Video) {
//                Video video = (Video)videoMsg;
//                Iterator<VideoType> iterator = video.getVideoType().iterator();
//                while(iterator.hasNext()) {
//                    VideoType videoType = iterator.next();
//                    exchange.publish(videoType.toString(), video.getMessage());
//                }
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void sendVideo(Object msg) {
        receiveVideo.offer(msg);
    }

    public static void main(String[] args) {
        exchange = new Exchange();
        try {
            String jsonData = Files.readString(Path.of(PATH));
            JSONObject jsonObject = JSON.parseObject(jsonData);

            for (Map.Entry<String, Object> platformEntry : jsonObject.entrySet()) {
                String platform = platformEntry.getKey();
                JSONObject platformData = (JSONObject) platformEntry.getValue();

                System.out.println("Platform: " + platform);
                System.out.println("platformData: " + platformData);

                String types = getStringValueOrDefault(platformData, "type", "");
                boolean isMatch = getBooleanValueOrDefault(platformData, "isMatch", false);

                Pattern pattern = Pattern.compile("\"([^\"]+)\"");
                Matcher matcher = pattern.matcher(types);

                while (matcher.find()) {
                    String type = matcher.group(1);
                    for (String queueName : platformData.keySet()) {
                        exchange.bind(new VideoQueue(platform + "-" + queueName, isMatch), type);
                    }
                }
            }

            receiveVideo = new ArrayBlockingQueue<>(1024);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String getStringValueOrDefault(JSONObject jsonObject, String key, String defaultValue) {
        Object value = jsonObject.get(key);
        return value != null ? value.toString() : defaultValue;
    }

    private static boolean getBooleanValueOrDefault(JSONObject jsonObject, String key, boolean defaultValue) {
        Object value = jsonObject.get(key);
        return value != null ? (boolean) value : defaultValue;
    }
}
