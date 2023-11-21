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
    @Resource
    AccountMapper accountMapper;
    @Test
    public void login() throws InterruptedException {
        //改为自己本地浏览器以及浏览器对应驱动
        System.setProperty("webdriver.chrome.driver", "D:\\downLoad\\driver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        options.setBinary("C:\\Program Files (x86)\\Chromebrowser\\Chrome.exe");
        String cookies = "[name=ssid_ucp_v1,value=1.0.0-KGNmZDJlMzYxMzc1MmNhYzY4NmEwOTA5MWRiMjI4Mzc5OTViOWU1NTAKFwjKg9zHzwIQw47yqgYY7zE4AkDxB0gEGgJsZiIgZDc1Y2RjYzgxMTgxMTE3NDcyMzk3YWE2YjJlNjc5OTg,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=toutiao_sso_user_ss,value=a892c14fd8b62a10234dbe73645b2513,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=dy_swidth,value=2560,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sid_ucp_v1,value=1.0.0-KGNmZDJlMzYxMzc1MmNhYzY4NmEwOTA5MWRiMjI4Mzc5OTViOWU1NTAKFwjKg9zHzwIQw47yqgYY7zE4AkDxB0gEGgJsZiIgZDc1Y2RjYzgxMTgxMTE3NDcyMzk3YWE2YjJlNjc5OTg,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=sessionid,value=d75cdcc81181117472397aa6b2e67998,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=publish_badge_show_info,value=%221%2C0%2C0%2C1700562753890%22,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=dy_sheight,value=1440,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=passport_fe_beating_status,value=true,domain=.www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sso_auth_status_ss,value=80c266014c883cd7b2af39ded21887b0,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=sso_uid_tt,value=25ceff6ddfee2558461b4b55181696d2,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=toutiao_sso_user,value=a892c14fd8b62a10234dbe73645b2513,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=uid_tt_ss,value=f5d753aba0ec2160f986121776034490,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=passport_auth_status,value=c6df4168e061a81c42aae6003ed7b9c6%2C020a2040b1c162f1b6c4c73a50a7020f,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=passport_csrf_token_default,value=05450e97a2922a34bcd08d7013fa4b35,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sid_ucp_sso_v1,value=1.0.0-KGIzYjlhYTBkODcwMDUzMzg5Y2ExNjM0MTQzZDJlMGE3NDZlNWIxZmUKFQjKg9zHzwIQwo7yqgYY7zE4AkDxBxoCaGwiIGE4OTJjMTRmZDhiNjJhMTAyMzRkYmU3MzY0NWIyNTEz,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=_bd_ticket_crypt_doamin,value=2,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sid_tt,value=d75cdcc81181117472397aa6b2e67998,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=ttcid,value=ff63d077d5b4424b9538dee482d8611955,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=__ac_nonce,value=0655c873a00d5dcedbdf4,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=passport_auth_status_ss,value=c6df4168e061a81c42aae6003ed7b9c6%2C020a2040b1c162f1b6c4c73a50a7020f,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=sessionid_ss,value=d75cdcc81181117472397aa6b2e67998,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=bd_ticket_guard_client_data,value=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCTENlcDNrK1FXU2pzM0xhaUk3bk1aUFBibUw2TzRmanpSNFdtNHdVR0Fpa1JQRmhDQmFLWmVjT3Q5by90VzZBSkJWWlNSNUd3U1J1ZU9SVjhCc1k2SFE9IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoxfQ%3D%3D,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=VIDEO_FILTER_MEMO_SELECT,value=%7B%22expireTime%22%3A1701167552643%2C%22type%22%3A1%7D,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=passport_csrf_token,value=05450e97a2922a34bcd08d7013fa4b35,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=false,sameSite=None, name=LOGIN_STATUS,value=1,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=store-region,value=cn-gx,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=home_can_add_dy_2_desktop,value=%220%22,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=stream_recommend_feed_params,value=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A2560%2C%5C%22screen_height%5C%22%3A1440%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A16%2C%5C%22device_memory%5C%22%3A8%2C%5C%22downlink%5C%22%3A10%2C%5C%22effective_type%5C%22%3A%5C%224g%5C%22%2C%5C%22round_trip_time%5C%22%3A50%7D%22,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=n_mh,value=r4CHf4ZyoLENs5dMlsfdQylST034abg1VJLdWQdi4F0,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=__security_server_data_status,value=1,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=ttwid,value=1%7CCe8h55ASk6Y5TN2vid0ZDTn5wbkB04oOmzFYQjo8FM0%7C1700562752%7Cff8184faf18a5f9ee9db219682a39633e4737091d92ece7a43a65b8540fa46de,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=device_web_cpu_core,value=16,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=stream_player_status_params,value=%22%7B%5C%22is_auto_play%5C%22%3A0%2C%5C%22is_full_screen%5C%22%3A0%2C%5C%22is_full_webscreen%5C%22%3A0%2C%5C%22is_mute%5C%22%3A0%2C%5C%22is_speed%5C%22%3A1%2C%5C%22is_visible%5C%22%3A1%7D%22,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=uid_tt,value=f5d753aba0ec2160f986121776034490,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=csrf_session_id,value=1b8a6bc751d9ad8f5296e31b474d4f1c,domain=www.douyin.com,path=/,isSecure=true,isHttpOnly=false,sameSite=None, name=sso_auth_status,value=80c266014c883cd7b2af39ded21887b0,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=IsDouyinActive,value=true,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=FORCE_LOGIN,value=%7B%22isForcePopClose%22%3A1%2C%22videoConsumedRemainSeconds%22%3A180%7D,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=ssid_ucp_sso_v1,value=1.0.0-KGIzYjlhYTBkODcwMDUzMzg5Y2ExNjM0MTQzZDJlMGE3NDZlNWIxZmUKFQjKg9zHzwIQwo7yqgYY7zE4AkDxBxoCaGwiIGE4OTJjMTRmZDhiNjJhMTAyMzRkYmU3MzY0NWIyNTEz,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=architecture,value=amd64,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=strategyABtestKey,value=%221700562722.299%22,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=volume_info,value=%7B%22isUserMute%22%3Afalse%2C%22isMute%22%3Afalse%2C%22volume%22%3A0.6%7D,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=msToken,value=iaC541h9mToMl87sjI0KnP3SEVBVGP4ndMZJcXAmTSACzKx_27A6xuVNCNtShNDBxlpUo7o1LU9ai4M3KH_v0-IxUeKarjjunmoW4Sfo69MOAcSfAxIvyQ-NyBnd,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=msToken,value=C8FQlaVjVIugeckZzpBPG9QjPlVi1a_jXOoPBi3EXMhrYHWcVnzVwFt1j0LaDwgsz0OlnW2e-EHqkS4uPIf1WeY7MWORnIKCLLtifhxkVH6ADMRMA7loZmOQhHQg,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=false,sameSite=None, name=device_web_memory_size,value=8,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=s_v_web_id,value=verify_lp874ze3_bA4HAXew_cJ1W_4b02_95CK_1XoMfWy5I96S,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sid_guard,value=d75cdcc81181117472397aa6b2e67998%7C1700562755%7C5183993%7CSat%2C+20-Jan-2024+10%3A32%3A28+GMT,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=null, name=passport_assist_user,value=CjyKcEN8_97gdpmsAKhwwjPNQsUpAlbFyuDahhBWtTgIZSONdkXe42j-cCs4ekTl3HNZvYmzbq6vrCa2-hMaSgo8tWsHN5cwBMNOyDq2fHvc1E9diWg4Pi33CIjrGVcDCyojS_0lbvdZL77p9bNORSDGoOgv43iWwsbES_N2EMTywQ0Yia_WVCABIgEDcXithg%3D%3D,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=false,sameSite=null, name=webcast_local_quality,value=null,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=sso_uid_tt_ss,value=25ceff6ddfee2558461b4b55181696d2,domain=.douyin.com,path=/,isSecure=true,isHttpOnly=true,sameSite=None, name=xgplayer_user_id,value=899502535665,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=_bd_ticket_crypt_cookie,value=f3d2bb747b4ca66c01ec1186544248e0,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null, name=store-region-src,value=uid,domain=.douyin.com,path=/,isSecure=false,isHttpOnly=true,sameSite=null, name=tt_scid,value=4Ao6DEnc857WF-GQ0GGJJloque954jWrtpmVydhEedtPt0ZM16rK0Xlnowmul51Gbf58,domain=www.douyin.com,path=/,isSecure=false,isHttpOnly=false,sameSite=null]";
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
        ChromeDriver confirmLogin = new ChromeDriver(options);
        confirmLogin.get("https://www.douyin.com/");
        confirmLogin.manage().deleteAllCookies();
        for (Cookie cookie : cookies1) {
            confirmLogin.manage().addCookie(cookie);
        }
        confirmLogin.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        confirmLogin.navigate().refresh();
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
