package org.example.core.publisher.impl;

import org.example.core.publisher.PlatformVideoPublisher;
import org.example.pojo.VideoToPublish;
import org.example.utils.GetScriptPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @Description
 * @Author welsir
 * @Date 2023/10/13 20:11
 */
@Component
public class DouyinVideoPublisher implements PlatformVideoPublisher {

    final String DOUYIN_PUBLISH_VIDEO = "org/example/core/script/douyin/DouyinVideoPublish.py";

    @Override
    public void publishVideo(VideoToPublish video) {
        try {
            System.setProperty("webdriver.chrome.driver", "D:\\downLoad\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files (x86)\\Chromebrowser\\Chrome.exe");
            ChromeDriver webDriver = new ChromeDriver(options);
            String url = "https://www.douyin.com/";
            webDriver.get(url);
            String cookies = video.getCookies().substring(1, video.getCookies().length() - 1);
            String[] split = cookies.split(", ");
            Set<Cookie> cookies1 = new HashSet<>();
            for (String s : split) {
                HashMap<String, String> map = new HashMap<>();
                String[] split1 = s.split(",");
                for (String s1 : split1) {
                    String[] split2 = s1.split("=");
                    map.put(split2[0],split2[1]);
                }
                Cookie cookie = new Cookie(map.get("name"), map.get("value"), map.get("path"), null);
                cookies1.add(cookie);
            }
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().deleteAllCookies();
            Thread.sleep(5000L);
            for (Cookie cookie : cookies1) {
                webDriver.manage().addCookie(cookie);
            }
            Thread.sleep(2000L);
            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            WebElement user = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[4]/div[1]/div/div[1]/header/div/div/div[2]/div/div/div[5]/div/a")));
            user.click();
            WebElement videoPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("")));
            videoPage.click();
            Set<String> windowHandles = webDriver.getWindowHandles();
            String[] array = windowHandles.toArray(new String[0]);
            webDriver.switchTo().window(array[1]);
            WebElement upload = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[1]/button")));
            upload.click();
            WebElement selectFile = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div/div/div[3]/div/div[1]/div/div[1]/div/label/input")));
            selectFile.sendKeys(video.getVideoPath());
            WebElement title = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div/div/div[2]/div[1]/div[2]/div/div/div/div[1]/div/div/input")));
            title.sendKeys(video.getTitle());
            WebElement content = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div/div/div[2]/div[1]/div[2]/div/div/div/div[2]/div")));
            content.sendKeys(video.getContent());
            WebElement cover = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div/div/div[2]/div[1]/div[5]/div/div[1]/div[1]/div[2]")));
            cover.sendKeys(video.getCoverPath());
            WebElement save = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div/div/div[2]/div[1]/div[15]/button[1]")));
            save.click();
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
