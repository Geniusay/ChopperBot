package org.example.account;

import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/24 16:51
 */
public class bilibiliTest {

    @Test
    public void getcookies() throws Exception {
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
        webDriver.quit();
        ChromeDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.get(url);
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.manage().deleteAllCookies();
        for (Cookie cookie : cookies) {
            chromeDriver.manage().addCookie(cookie);
        }
        chromeDriver.navigate().refresh();
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
    }

    //读取硬盘cookie的操作
    public static Set<Cookie> read() throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d:\\4.txt"));
        HashSet<Cookie> cookies = (HashSet<Cookie>) objectInputStream.readObject();
        return cookies;
    }

    @Test
    public void testFile(){
        String filePath = "D://file//a.mp4";

        // 使用 Paths.get 方法创建 Path 对象
        Path path = FileSystems.getDefault().getPath(filePath);

        // 获取文件名
        String fileName = path.getFileName().toString();
        System.out.println("File Name: " + fileName);

        // 获取目录路径
        Path directoryPath = path.getParent();
        String directory = (directoryPath != null) ? directoryPath.toString() : "No Directory"; // 处理根目录的情况
        System.out.println("Directory: " + directory);


        HashMap<String, List<String>> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.putIfAbsent("123",new ArrayList<>());
        List<String> strings = objectObjectHashMap.get("123");
        strings.add("zzzz");
        System.out.println(objectObjectHashMap.get("123"));
    }

    @Test
    public void testCookies(){
        String cookies = "[name=ssid_ucp_v1,value=1.0.0-KGI3NTQ3NjlmNmQ0M2RiMDQxYjAzZDBlNmI1ODA2ODQwNDMwNTQ0NTkKFwjKg9zHzwIQnNPxqgYY7zE4AkDxB0gEGgJobCIgZTNmZDBkZjI2NzEwODBiZjBiMGNhOWFmYjQ5ZGNhZTg,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=toutiao_sso_user_ss,value=7ae2c0506c77914bdc4cd547b1de6dc3,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=dy_swidth,value=2560,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sid_ucp_v1,value=1.0.0-KGI3NTQ3NjlmNmQ0M2RiMDQxYjAzZDBlNmI1ODA2ODQwNDMwNTQ0NTkKFwjKg9zHzwIQnNPxqgYY7zE4AkDxB0gEGgJobCIgZTNmZDBkZjI2NzEwODBiZjBiMGNhOWFmYjQ5ZGNhZTg,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=sessionid,value=e3fd0df2671080bf0b0ca9afb49dcae8,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=publish_badge_show_info,value=%220%2C0%2C0%2C1700555163451%22,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=dy_sheight,value=1440,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=passport_fe_beating_status,value=true,domain=.www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sso_auth_status_ss,value=bef69c1d1ece7282e7bafd32a1d8285d,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=sso_uid_tt,value=4d9e60a2831d4e4d03def714ca6aaad6,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=toutiao_sso_user,value=7ae2c0506c77914bdc4cd547b1de6dc3,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=passport_auth_status,value=417058c10e08078e900b3e77cf2251cf%2Ccd4af14773bcb72d4888b8776ecb2846,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=uid_tt_ss,value=539fc71fee0a3d269a739f627bc5756e,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=passport_csrf_token_default,value=1cf23be5d159c74c3160df9775274994,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sid_ucp_sso_v1,value=1.0.0-KGE2ODZkODRiNjQ0NjlmNGYwMTNiNGQ5ZmMwZjE2ZjUyZDBiNWI2ODEKFQjKg9zHzwIQnNPxqgYY7zE4AkDxBxoCbHEiIDdhZTJjMDUwNmM3NzkxNGJkYzRjZDU0N2IxZGU2ZGMz,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=_bd_ticket_crypt_doamin,value=2,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sid_tt,value=e3fd0df2671080bf0b0ca9afb49dcae8,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=ttcid,value=9133444bf4224ec68b4cf4aa24a4236c27,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=__ac_nonce,value=0655c699300d7aa3c4d40,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=passport_auth_status_ss,value=417058c10e08078e900b3e77cf2251cf%2Ccd4af14773bcb72d4888b8776ecb2846,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=sessionid_ss,value=e3fd0df2671080bf0b0ca9afb49dcae8,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=bd_ticket_guard_client_data,value=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCSjNQUjFIbW85SkZyVjFsMVhDMTljZS9ab3ZxYU9oemI5YysxREZlN3pNSXBObHRLU283cEpWZDV3Nzl4Q1EzVE80cERicVNwd1p0K3lwcllGQzhncm89IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoxfQ%3D%3D,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=VIDEO_FILTER_MEMO_SELECT,value=%7B%22expireTime%22%3A1701159962231%2C%22type%22%3A1%7D,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=passport_csrf_token,value=1cf23be5d159c74c3160df9775274994,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=false,sameSite=None, name=LOGIN_STATUS,value=1,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=store-region,value=cn-gx,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=home_can_add_dy_2_desktop,value=%220%22,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=FOLLOW_NUMBER_YELLOW_POINT_INFO,value=%22MS4wLjABAAAAZHSYc8vPCtW-v3hSHDZTiFkWQxJbk3IvqP9zyksaP9w%2F1700582400000%2F0%2F1700555161771%2F0%22,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=stream_recommend_feed_params,value=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A2560%2C%5C%22screen_height%5C%22%3A1440%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A16%2C%5C%22device_memory%5C%22%3A8%2C%5C%22downlink%5C%22%3A10%2C%5C%22effective_type%5C%22%3A%5C%224g%5C%22%2C%5C%22round_trip_time%5C%22%3A50%7D%22,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=n_mh,value=r4CHf4ZyoLENs5dMlsfdQylST034abg1VJLdWQdi4F0,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=__security_server_data_status,value=1,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=ttwid,value=1%7Cm_cYPgXAiSUJm779cuSg4F1sVYutE0gTTsRApGRcCfk%7C1700555161%7Cac30702cc767d483a1133ba576356073f32fac6d7af79ed2c8e92e29924c886a,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=device_web_cpu_core,value=16,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=stream_player_status_params,value=%22%7B%5C%22is_auto_play%5C%22%3A0%2C%5C%22is_full_screen%5C%22%3A0%2C%5C%22is_full_webscreen%5C%22%3A0%2C%5C%22is_mute%5C%22%3A0%2C%5C%22is_speed%5C%22%3A1%2C%5C%22is_visible%5C%22%3A1%7D%22,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=uid_tt,value=539fc71fee0a3d269a739f627bc5756e,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=csrf_session_id,value=1b8a6bc751d9ad8f5296e31b474d4f1c,domain=www.douyin.com,path=/,isSecure=true,isHttpOnly=false,sameSite=None, name=sso_auth_status,value=bef69c1d1ece7282e7bafd32a1d8285d,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=IsDouyinActive,value=true,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=FORCE_LOGIN,value=%7B%22videoConsumedRemainSeconds%22%3A180%7D,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=ssid_ucp_sso_v1,value=1.0.0-KGE2ODZkODRiNjQ0NjlmNGYwMTNiNGQ5ZmMwZjE2ZjUyZDBiNWI2ODEKFQjKg9zHzwIQnNPxqgYY7zE4AkDxBxoCbHEiIDdhZTJjMDUwNmM3NzkxNGJkYzRjZDU0N2IxZGU2ZGMz,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=architecture,value=amd64,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=strategyABtestKey,value=%221700555162.484%22,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=volume_info,value=%7B%22isUserMute%22%3Afalse%2C%22isMute%22%3Afalse%2C%22volume%22%3A0.6%7D,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=msToken,value=FZTvpM6l7LgPtbgIvsaZOziX1Dddu0nxBfe6yd5MLYX78yGDvyYpvWoyUff-jiDSwCv7sVOa8tGpe72ju-heOwgCMM8Ps7JaeZ001777gTjZhd3jtUQBCkKZ78apG3I=,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=msToken,value=wTUpTX35KPFtcAGtl_rOEPyeIGD54rCjDKDwGhxqLp0g5vcJXRt2fSVGMbZr2oFwYQPz7mAp_I9s0S1BM4Q6IQKaixh9sS3-Hb8yZKEaXMDXbQA1lvtX8B3avh6FU04=,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=false,sameSite=None, name=device_web_memory_size,value=8,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=s_v_web_id,value=verify_lp82m826_jVkmFtQv_FCza_4r8k_Adaz_Pq1Wol3HqF8l,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sid_guard,value=e3fd0df2671080bf0b0ca9afb49dcae8%7C1700555164%7C5183993%7CSat%2C+20-Jan-2024+08%3A25%3A57+GMT,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=passport_assist_user,value=CjwM1LOHT2W9rrHL4ywq27T3RYF1Vr4Wy1uaWLyPUg2JcWsiOykmkJmqDHO0zikBmxwoHy1BNGKLNXcQ4DAaSgo8MBX1d_2t248a86IzoSStp82PrQeGwMvm5GVdrPNbfnPNVVGNEumR-qqI8ML6thDLtElAGrMdNsHXYEvbEPjvwQ0Yia_WVCABIgEDNzJZfw%3D%3D,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=false,sameSite=null, name=webcast_local_quality,value=null,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sso_uid_tt_ss,value=4d9e60a2831d4e4d03def714ca6aaad6,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=_bd_ticket_crypt_cookie,value=95115ff396607fdeca691aeeaf9977cd,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=store-region-src,value=uid,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=tt_scid,value=L5ahuGUC2r5jb9mcbPyNKDuEjon8GW-IB3S1rwmwC.Q4sXgsBMllasV9YAv9QgYFee65,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null]";
        cookies = cookies.substring(1, cookies.length() - 1);
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
        for (Cookie cookie : cookies1) {
            System.out.println(cookie);
        }
    }

}
