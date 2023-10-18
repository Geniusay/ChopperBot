package org.example.core.platform;

import org.example.core.factory.PlatformOperation;
import org.example.mapper.AccountMapper;

import org.example.pojo.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @Description
 * @Author welsir
 * @Date 2023/9/24 21:18
 */
@Component
public class Bilibili implements PlatformOperation {

    private static final String URL = "https://www.bilibili.com/";

    @Override
    public Set<Cookie> login(int id,String username) {
        try {
            System.setProperty("webdriver.chrome.driver", "D:\\downLoad\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files (x86)\\Chromebrowser\\Chrome.exe");
            ChromeDriver loginwebDriver = new ChromeDriver(options);
            loginwebDriver.get(URL); //
            loginwebDriver.manage().deleteAllCookies();
            Thread.sleep(30000L);
            Set<Cookie> cookies = loginwebDriver.manage().getCookies();
            loginwebDriver.quit();
            ChromeDriver confirmLogin = new ChromeDriver(options);
            confirmLogin.get(URL);
            confirmLogin.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            confirmLogin.manage().deleteAllCookies();
            for (Cookie cookie : cookies) {
                confirmLogin.manage().addCookie(cookie);
            }
            confirmLogin.navigate().refresh();
            Thread.sleep(3000L);
            WebElement avator = confirmLogin.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[1]/ul[2]/li[1]"));
            if(avator!=null){
                System.out.println("登陆成功!");
                confirmLogin.quit();
                return cookies;
            }else{
                throw new RuntimeException("登陆失败!");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
