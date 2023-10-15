package org.example.core.platform;

import org.example.core.factory.PlatformOperation;
import org.example.mapper.AccountMapper;

import org.example.core.pojo.Account;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Resource;
import java.util.*;


/**
 * @Description
 * @Author welsir
 * @Date 2023/9/24 21:18
 */
public class Bilibili implements PlatformOperation {

    //cookie本地读取路径
    private static final String FILE_PATH = "D:\\Douyincookies.txt";
    private static final String URL = "https://www.bilibili.com/";
    @Resource
    private AccountMapper accountMapper;

    @Override
    public void login(int id,String username,String password) {
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
            confirmLogin.manage().deleteAllCookies();
            for (Cookie cookie : cookies) {
                loginwebDriver.manage().addCookie(cookie);
            }
            confirmLogin.navigate().refresh();
            Thread.sleep(3000L);
            WebElement avator = confirmLogin.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[1]/ul[2]/li[1]"));
            if(avator!=null){
//                Account user = new Account();
//                user.setCookies(cookies.toString());
//                user.setPlatformId(1);
//                user.setUsername(username);
//                user.setPassword(password);
//                accountMapper.insert(user);
                System.out.println("登陆成功!");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
