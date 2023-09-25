package org.example.core.guard;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.pojo.VideoType;
import org.example.core.exchange.Exchange;
import org.example.core.mapper.AccountMapper;
import org.example.core.mapper.AccountTypeMapper;
import org.example.plugin.GuardPlugin;
import org.example.pojo.Account;
import org.example.pojo.AccountType;
import org.example.pojo.Video;
import org.example.pojo.VideoQueue;

import javax.annotation.Resource;
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

    private Exchange exchange;
    private static final String ACCOUNT_PATH = "config/Account/account.json";
    private BlockingQueue<Object> receiveVideo;

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountTypeMapper accountTypeMapper;


    public VideoPushGuard(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
    }

    @Override
    public boolean init() {
        exchange = new Exchange();
        try {

            List<Account> accountList = accountMapper.selectList(null);

            for (Account account : accountList) {
                List<AccountType> accountTypes = accountTypeMapper.selectList(new QueryWrapper<AccountType>().eq("uid",account.getId()));

            }


            String jsonData = Files.readString(Path.of(ACCOUNT_PATH));
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
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() {
        try {
            Object videoMsg = receiveVideo.poll(5, TimeUnit.SECONDS);
            if (videoMsg instanceof Video) {
                Video video = (Video)videoMsg;
                Iterator<VideoType> iterator = video.getVideoType().iterator();
                while(iterator.hasNext()) {
                    VideoType videoType = iterator.next();
                    exchange.publish(videoType.toString(), video.getMessage());
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendVideo(Object msg) {
        receiveVideo.offer(msg);
    }
    private String getStringValueOrDefault(JSONObject jsonObject, String key, String defaultValue) {
        Object value = jsonObject.get(key);
        return value != null ? value.toString() : defaultValue;
    }

    private boolean getBooleanValueOrDefault(JSONObject jsonObject, String key, boolean defaultValue) {
        Object value = jsonObject.get(key);
        return value != null ? (boolean) value : defaultValue;
    }
}
