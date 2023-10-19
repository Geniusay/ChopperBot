package org.example.core.platform;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import org.example.core.factory.PlatformOperation;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/9 17:09
 */

public class Douyin implements PlatformOperation {

    final String FILE_PATH = "D:\\Douyincookies.txt";
    final String LOGIN_SCRIPT_PATH = "org/example/core/script/douyin/Login.py";

    final String CONFIRM_LOGIN_SCRIPT_PATH = "org/example/core/script/douyin/ConfirmLogin.py";
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
            ChromeDriver confirmLogin = new ChromeDriver(options);
            confirmLogin.get(URL);
            confirmLogin.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            confirmLogin.manage().deleteAllCookies();
            for (Cookie cookie : cookies) {
                if("".equals(cookie.getName())||cookie.getName()==null){
                    continue;
                }
                confirmLogin.manage().addCookie(cookie);
            }
            confirmLogin.navigate().refresh();
            Thread.sleep(3000L);
            WebElement avator = confirmLogin.findElement(By.xpath("/html/body/div[2]/div[1]/div[4]/div[1]/div[1]/header/div/div/div[2]/div/div/div[6]/div/a"));
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
