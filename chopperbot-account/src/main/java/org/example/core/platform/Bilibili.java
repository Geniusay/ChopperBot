package org.example.core.platform;

import org.example.core.factory.PlatformOperation;
import org.example.core.mapper.AccountMapper;
import org.example.pojo.Account;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Resource;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/24 21:18
 */
public class Bilibili implements PlatformOperation {
    @Resource
    private AccountMapper accountMapper;

    Set<Cookie> cookies;
    @Override
    public void operation(int id) {
        try {
            //设置driver驱动，<-记得更改为自己的本地路径!!->
            System.setProperty("webdriver.chrome.driver", "D:\\downLoad\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            //同理
            options.setBinary("C:\\Program Files (x86)\\Chromebrowser\\Chrome.exe");
            ChromeDriver webDriver = new ChromeDriver(options);
            //b站网址
            String url = "https://www.bilibili.com/";
            webDriver.get(url);
            //与浏览器同步非常重要，必须等待浏览器加载完毕
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //中间完成登录操作
            Scanner scanner = new Scanner(System.in);
            scanner.next();
            //获取cookie
            cookies = webDriver.manage().getCookies();
            Account account = new Account();
            account.setCookie(cookies);
            account.setPlatformId(id);
            accountMapper.insert(account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
