package org.example.core.parser.impl;

import org.example.core.creeper.loadconfig.DouyuRecordConfig;
import org.example.core.parser.PlatformVideoUrlParser;
import org.example.pojo.live.LiveConfig;
import org.example.pool.ConstPool;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.network.Network;
import org.openqa.selenium.devtools.v115.network.model.Response;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * 斗鱼M3u8链接解析
 * @author 燧枫
 * @date 2023/8/3 21:52
 */
public class DouyuM3u8UrlParser implements PlatformVideoUrlParser<DouyuRecordConfig> {

    private static String DOUYE_RECORD_M3U8 = "https://v.douyu.com/show/%s";

    private static final Logger logger = Logger.getLogger(DouyuM3u8UrlParser.class.getName());

    @Override
    public String getUrl(DouyuRecordConfig douyuRecordConfig) {

        // 设置 WebDriver 的路径
        System.setProperty(ConstPool.EDGE_DRIVER_PATH, "E:\\edgDriver\\msedgedriver.exe");

        // 创建 EdgeOptions 实例
        EdgeOptions edgeOptions = new EdgeOptions();
        // 设置为无头模式
        edgeOptions.addArguments("--headless");

        // 创建 WebDriver
        EdgeDriver driver = new EdgeDriver(edgeOptions);

        // 创建 DevTools 并建立会话
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // 启用 Network
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // 创建一个数组来存储获取到的视频链接
        final String[] videoUrl = new String[1];

        // 添加监听器，监听 HTTP 响应
        devTools.addListener(Network.responseReceived(), response -> {
            Response responseReceived = response.getResponse();
            String url = responseReceived.getUrl();
            if (url.contains("https://play-tx-recpub.douyucdn2.cn/live/") && url.contains("m3u8")) {
                videoUrl[0] = url;
            }
        });

        // 打开网页
        driver.get(String.format(DOUYE_RECORD_M3U8, douyuRecordConfig.getVid()));
        logger.info("成功打开网页");

        // 添加延迟以便获取链接
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭浏览器和驱动
        driver.quit();

        logger.info("获取到视频链接: " + videoUrl[0]);

        return videoUrl[0];
    }
}
