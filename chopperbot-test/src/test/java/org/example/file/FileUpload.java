package org.example.file;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.xalan.xsltc.util.IntegerArray;
import org.example.ConsoleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/8 20:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FileUpload {

    private final String AppID = "42617278";
    private final String AppKey = "zBZVIE1GLyPr3sqCzMdxdcjFdbyuIAUW";
    private final String Secretkey = "9bP4I2ffdaQNHG8lZ3Ni4K9Dqt2LzmfM";
    private final String Signkey = "A%v@$sdW~M8tj$FOY9!~sOR*=TIA@HOz";
    private final String code = "9761adc8f7bc0be486a28374d56c3871";
    private final String account = "13078050925";
    private final String password = "Awjy20020225";
    private String access_token = "121.2ff927e79bc531c38bf2e2657234adc1.YHa_oIipkNUhzsCNUXbokKu7GoRSaznKnN5oTTn.WFk6UA";
    private String refresh_token = "122.f773ac5b00c08361dcc96024bd09acb9.YQNDhwbojaIo7UTzURr3O_QjCacYgTW2cP2SfhD._iEaDA";
    private final String HOST = "http://pan.baidu.com/";
    private final String filePath = "C:\\Users\\18243\\Desktop\\微信图片_20231108221347.jpg";
    @Test
    public void BaiduTest() throws IOException, InterruptedException {
        //1.用户需要手动提供code
//        OkHttpClient post = new OkHttpClient();
//

//        Request request = new Request.Builder().url(url).build();
//        Response response = post.newCall(request).execute();
//        assert response.body() != null;
//        System.out.println(response.body().string());


        //2.获取access_token
//        String url = "https://openapi.baidu.com/oauth/2.0/token?\n" +
//                "grant_type=authorization_code&\n" +
//                "code="+code+"&\n" +
//                "client_id="+AppKey+"&\n" +
//                "client_secret="+Secretkey+"&\n" +
//                "redirect_uri=oob";
//        Request build = new Request.Builder().url(url).build();
//        OkHttpClient client = new OkHttpClient();
//        Response response = client.newCall(build).execute();
//        assert response.body() != null;
//        String res = response.body().string();
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(res);
//        System.out.println(jsonNode.toString());
//        String refreshToken = jsonNode.get("refresh_token").asText();
//        String accessToken = jsonNode.get("access_token").asText();
//
//        System.out.println("refresh_token: " + refreshToken);
//        System.out.println("access_token: " + accessToken);
//        3.上传文件
        String url = HOST+"/rest/2.0/xpan/file?method=precreate&access_token="+access_token;
        OkHttpClient client = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();
        File file = new File(filePath);
        builder.add("path",filePath);
        builder.add("size",String.valueOf(file.length()));
        builder.add("isdir","0");
        builder.add("block_list","");
        builder.add("autoinit","1");
        FormBody body = builder.build();
        Map<String, String> httpHeaders = null;
        httpHeaders.put("User-Agent","pan.baidu.com");
        httpHeaders.put("Content-Type","application/x-www-form-urlencoded");
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).execute();
        long splitSize = 4 * 1024 * 1024;//单片文件大小,4MB
        if(file.length()<=splitSize){

        }else {

        }
//        Request userInfo = new Request.Builder().url(HOST + "/rest/2.0/xpan/nas?method=uinfo&access_token="+access_token).build();
//        Response userInfoResponse = client.newCall(userInfo).execute();
//        System.out.println(userInfoResponse.body().string());
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//
//        Request.Builder builder = new Request.Builder()
//                .addHeader("User-Agent", "pan.baidu.com")
//                .url(HOST);
    }
}
