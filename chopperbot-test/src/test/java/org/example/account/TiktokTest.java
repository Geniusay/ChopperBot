package org.example.account;

import org.example.ConsoleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/19 15:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TiktokTest {

    @Test
    public void pushVideo() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\downLoad\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files (x86)\\Chromebrowser\\Chrome.exe");
        ChromeDriver webDriver = new ChromeDriver(options);
        String url = "https://www.tiktok.com/";
        webDriver.get(url);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        Set<Cookie> cookies = webDriver.manage().getCookies();
        baocun(cookies);
        webDriver.quit();
        ChromeDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.get(url);
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Set<Cookie> cookieSet = read();
        chromeDriver.manage().deleteAllCookies();
        for (Cookie cookie : cookieSet) {
            chromeDriver.manage().addCookie(cookie);
        }
        chromeDriver.navigate().refresh();
        WebElement uploadButton = chromeDriver.findElement(By.xpath("//*[@id=\"app-header\"]/div/div[3]/div[1]/a"));
        uploadButton.click();
        WebDriverWait wait = new WebDriverWait(chromeDriver, 10);
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("/html/body/div/div[2]/div[2]/div/div/iframe")));
        chromeDriver.switchTo().frame(iframe);
        System.out.println(iframe);
        WebElement file = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("/html/body/div[1]/div/div/div/div/div/div/div/div/div[4]/button")));
        System.out.println(file);
        String filePath = "D:\\soft\\QQ\\1824379011\\FileRecv\\MobileFile\1_1.mp4";
        file.sendKeys(filePath);
        WebElement upload = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div/div[2]/div[2]/div[8]/div[2]/button")));
        upload.click();
    }

    @Test
    public void updateUser() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\downLoad\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files (x86)\\Chromebrowser\\Chrome.exe");
//        ChromeDriver webDriver = new ChromeDriver(options);
        String url = "https://www.tiktok.com/";
        ChromeDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.get(url);
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Set<Cookie> cookieSet = read();
        chromeDriver.manage().deleteAllCookies();
        for (Cookie cookie : cookieSet) {
            chromeDriver.manage().addCookie(cookie);
        }
        chromeDriver.navigate().refresh();
        String account = "@user616845559";
        chromeDriver.get(url+account);
        WebDriverWait wait = new WebDriverWait(chromeDriver, 10);
        WebElement update = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div[1]/div[1]/div[2]/div/button")));
        update.click();
        WebElement form = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("/html/body/div[6]/div/div[2]/div/div/div[2]/div/div/section")));
        WebElement text = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("/html/body/div[6]/div/div[2]/div/div/div[2]/div/div/section/div/div[2]/div[4]/div[2]/textarea")));
        text.clear();
        text.sendKeys("hahahahaha");
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("/html/body/div[6]/div/div[2]/div/div/div[2]/div/div/section/div/div[3]/button[2]")));
        save.click();
        Thread.sleep(10000L);
        chromeDriver.navigate().refresh();
    }
    public static void baocun(Set<Cookie> cookies) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d:\\4.txt"));
        objectOutputStream.writeObject(cookies);
    }

    public static Set<Cookie> read() throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d:\\4.txt"));
        HashSet<Cookie> cookies = (HashSet<Cookie>) objectInputStream.readObject();
        return cookies;
    }
}
