package org.example.core.platform;

import org.example.core.factory.PlatformOperation;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/9 17:09
 */

public class Douyin implements PlatformOperation {
    final String URL ="https://www.douyin.com/";
    @Override
    public Set<org.openqa.selenium.Cookie> login(int id, String username) {
        try {
            System.setProperty("webdriver.chrome.driver", "D:\\downLoad\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files (x86)\\Chromebrowser\\Chrome.exe");
            ChromeDriver loginwebDriver = new ChromeDriver(options);
            loginwebDriver.get(URL); //
            loginwebDriver.manage().deleteAllCookies();
            Thread.sleep(45000L);
            Set<Cookie> cookies = loginwebDriver.manage().getCookies();
            loginwebDriver.quit();
            HashSet<Cookie> set = new HashSet<>();
            for (Cookie cookie : cookies) {
                if("".equals(cookie.getName())||cookie.getName()==null){
                    continue;
                }
                set.add(cookie);
            }
            System.out.println("登陆成功!");
            return set;
        } catch (InterruptedException e) {
            System.out.println("登陆失败!");
            throw new RuntimeException(e);
        }
    }
}
