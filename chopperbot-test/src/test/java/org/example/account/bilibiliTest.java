package org.example.account;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/24 16:51
 */
public class bilibiliTest {

    public static void getcookies() throws Exception {

        System.setProperty("webdriver.chrome.driver", "D:\\downLoad\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files (x86)\\Chromebrowser\\Chrome.exe");
        ChromeDriver webDriver = new ChromeDriver(options);
        String url = "https://www.bilibili.com/";
        webDriver.get(url);
        // 与浏览器同步非常重要，必须等待浏览器加载完毕
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //中间完成登录操作
        Scanner scanner = new Scanner(System.in);
        scanner.next();
//        获取cookie,再通过baocun函数进行序列化操作
        Set<Cookie> cookies = webDriver.manage().getCookies();
        System.out.println(cookies);
        baocun(cookies);
    }

    //把登录后得到的cookie序列化到硬盘中
    public static void baocun(Set<Cookie> cookies) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d:\\4.txt"));
        objectOutputStream.writeObject(cookies);
    }
    public static void main(String[] args) throws Exception {
//        System.setProperty("webdriver.chrome.driver", "D:\\downLoad\\chromedriver_win32\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("download.default_directory", "d:\\");
//        options.setExperimentalOption("prefs", prefs);
//        options.setBinary("C:\\Program Files (x86)\\Chromebrowser\\Chrome.exe");
//        ChromeDriver webDriver = new ChromeDriver(options);
////       WangyiUtils.get cookies();
//        Set<Cookie> read = read();
//
//
//        String url = "https://www.bilibili.com/";
//        webDriver.get(url); //
//        webDriver.manage().deleteAllCookies();
//        for (Cookie cookie : read) {
//            webDriver.manage().addCookie(cookie);
//        }
//        // 与浏览器同步非常重要，必须等待浏览器加载完毕
//        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        webDriver.navigate().refresh();
        getcookies();
    }

    //读取硬盘cookie的操作
    public static Set<Cookie> read() throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d:\\4.txt"));
        HashSet<Cookie> cookies = (HashSet<Cookie>) objectInputStream.readObject();
        return cookies;
    }

}
