package org.example.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import org.example.ConsoleApplication;
import org.example.mapper.AccountMapper;
import org.example.pojo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.example.utils.GetScriptPath.getScriptPath;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/18 19:26
 */
public class DouyinTest {

    final String FILE_PATH = "D:\\Douyincookies.txt";
    final String LOGIN_SCRIPT_PATH = "org/example/core/script/douyin/Login.py";

    final String CONFIRM_LOGIN_SCRIPT_PATH = "org/example/core/script/douyin/ConfirmLogin.py";
    @Resource
    AccountMapper accountMapper;
    @Test
    public void login() throws InterruptedException {
        //改为自己本地浏览器以及浏览器对应驱动
        System.setProperty("webdriver.chrome.driver", "D:\\downLoad\\driver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        options.setBinary("C:\\Program Files (x86)\\Chromebrowser\\Chrome.exe");
        ChromeDriver loginWebDriver = new ChromeDriver(options);
        loginWebDriver.get("https://www.douyin.com/"); //
        loginWebDriver.manage().deleteAllCookies();
        Thread.sleep(50000L);
        Set<Cookie> cookies = loginWebDriver.manage().getCookies();
        loginWebDriver.quit();

        ChromeDriver confirmLogin = new ChromeDriver(options);
        confirmLogin.get("https://www.douyin.com/");
        confirmLogin.manage().deleteAllCookies();
        for (Cookie cookie : cookies) {
            confirmLogin.manage().addCookie(cookie);
        }
        confirmLogin.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        confirmLogin.navigate().refresh();
        WebElement avator = confirmLogin.findElement(By.xpath("/html/body/div[2]/div[1]/div[4]/div[2]/div[1]/header/div/div/div[2]/div/div/div[6]/div/a"));
        if(avator!=null){
//            Account user = new Account();
//            user.setCookies(cookies.toString());
//            user.setPlatformId(1);
//            user.setUsername(username);
//            accountMapper.insert(user);
            System.out.println("登陆成功!");
        }
    }

    //从本地文件获取cookie
    private HashSet<Cookie> loadCookiesFromFile (String filePath) throws IOException {
        HashSet<Cookie> cookies = new HashSet<>();
        try (FileReader fileReader = new FileReader(filePath)) {
            JSONReader reader = new JSONReader(fileReader);
            JSONArray jsonArray = reader.readObject(JSONArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String value = jsonObject.getString("value");
                String domain = jsonObject.getString("domain");
                String path = jsonObject.getString("path");
                String sameSite = jsonObject.getString("sameSite");
                Date expiry = jsonObject.getDate("expiry");
                boolean secure = jsonObject.getBoolean("secure");
                boolean httpOnly = jsonObject.getBoolean("httpOnly");
                Cookie cookie = new Cookie(name, value, domain, path, expiry, secure, httpOnly, sameSite);
                cookies.add(cookie);
            }
        }
        return cookies;
    }

}
