package org.example.core.platform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.core.factory.PlatformOperation;
import org.example.core.mapper.AccountMapper;
import org.example.pojo.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public void login(int id,String account,String password) {
        try {
            // 读取Python脚本的输出
            List<String> command = new ArrayList<>();
            command.add("python"); // Python 解释器
            command.add("D:\\code\\gitHubProject\\Text_select_captcha\\bilbil.py"); // 要运行的 Python 脚本文件名
            //第一次登录需要提供账号密码
            command.add(account); // 第一个参数
            command.add(password); // 第二个参数 账号
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            // 等待Python脚本执行完成
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Python脚本执行成功！");
            } else {
                System.err.println("Python脚本执行失败！");
            }

            System.setProperty("webdriver.chrome.driver", "D:\\downLoad\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.setBinary("C:\\Program Files (x86)\\Chromebrowser\\Chrome.exe");
            ChromeDriver webDriver = new ChromeDriver(options);
            Set<Cookie> read = readCookiesByLocal();

            webDriver.get(URL); //
            webDriver.manage().deleteAllCookies();
            for (Cookie cookie : read) {
                webDriver.manage().addCookie(cookie);
            }
            webDriver.navigate().refresh();
            Thread.sleep(3000L);
            WebElement avator = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[1]/ul[2]/li[1]"));
            if(avator!=null){
                Account user = new Account();
                user.setCookies(read.toString());
                user.setPlatformId(1);
                user.setUsername("13078050925");
                user.setPassword("wjy20020225");
                accountMapper.insert(user);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private Set<Cookie> readCookiesByLocal() throws IOException {
        Set<Cookie> cookies = new HashSet<>();
        String jsonContent = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
        JSONArray jsonArray = JSON.parseArray(jsonContent);
        // 遍历 JSONArray 并解析为 Cookie 对象
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String path = jsonObject.getString("path");
            String domain = jsonObject.getString("domain");
            String sameSite = jsonObject.getString("sameSite");
            String name = jsonObject.getString("name");
            String expiry = jsonObject.getString("expiry");
            String httpOnly = jsonObject.getString("httpOnly");
            String secure = jsonObject.getString("secure");
            String value = jsonObject.getString("value");

            // 其他 Cookie 属性的解析
            long timestamp = Long.parseLong(expiry);

            // 将 Unix 时间戳转换为 Date 对象
            Date date = new Date(timestamp * 1000);
            Cookie cookie = new Cookie.Builder(name,value).domain(domain).path(path).expiresOn(date).sameSite(sameSite).isHttpOnly(Boolean.parseBoolean(httpOnly)).isSecure(Boolean.parseBoolean(secure)).build();
            cookies.add(cookie);
        }
        return cookies;
    }
}
