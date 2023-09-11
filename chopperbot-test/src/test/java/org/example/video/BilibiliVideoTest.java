package org.example.video;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.aspectj.weaver.ast.ITestVisitor;
import org.example.utils.HttpClientUtil;
import org.openqa.selenium.devtools.v113.network.model.TrustTokenOperationDone;

import java.io.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dhx
 * @date 2023/9/9 0:47
 */
public class BilibiliVideoTest {
    // 视频路径
    public static String inputVideoPath = "";

    // 视频分段暂存路径
    public static String outputDirectory = "";

    // B站Cookie
    public static String Cookie = "";
    public static void main(String[] args) {
        String jctPart = Cookie.substring(Cookie.indexOf("bili_jct"));
        String csrf;
        try{
            csrf = jctPart.substring(jctPart.indexOf("=")+1,jctPart.indexOf(";"));
            System.out.println(csrf);
        }catch (Exception e){
            System.out.println("Cookie错误");
            e.printStackTrace();
            return;
        }
        Map<String, String> header = new HashMap<>();
        int filesize = 0;
        FileInputStream fileInputStream1 = null;
        try {
            File file = new File(inputVideoPath);
            if (file.exists() && file.isFile()) {
                fileInputStream1 = new FileInputStream(file);
                filesize = fileInputStream1.available();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        header.put("Cookie", Cookie);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36 Edg/116.0.1938.69");
        header.put("Referer", "https://member.bilibili.com/platform/upload/video/frame?spm_id_from=333.1007.top_bar.upload");
        String res = HttpClientUtil.get("https://member.bilibili.com/preupload?probe_version=20221109&upcdn=bda2&zone=cs&name=test.mp4&r=upos&profile=ugcfx%2Fbup&ssl=0&version=2.14.0.0&build=2140000&size=" + filesize + "&webVersion=2.14.0"
                , header);
        JSONObject mp4Obj = JSONObject.parseObject(res);
        String mp4_upos_uri = mp4Obj.getString("upos_uri");
        Long biz_id = mp4Obj.getLong("biz_id");
        String endpoint = mp4Obj.getString("endpoint");
        String put_query = mp4Obj.getString("put_query");
        Long chunk_size = mp4Obj.getLong("chunk_size");
        String auth = mp4Obj.getString("auth");
        System.out.println(mp4_upos_uri);
        String res2 = HttpClientUtil.get("https://member.bilibili.com/preupload?name=file_meta.txt&size=2000&r=upos&profile=fxmeta%2Fbup&ssl=0&version=2.14.0.0&build=2140000&webVersion=2.14.0"
                , header);
        JSONObject txtObj = JSONObject.parseObject(res2);
        String meta_upos_uri = txtObj.getString("upos_uri");
        System.out.println(meta_upos_uri);
        String preUploadUrl = String.format("https:%s%s?uploads&output=json%s&filesize=%s&partsize=%s&meta_upos_uri=%s&biz_id=%s", endpoint, mp4_upos_uri.substring(mp4_upos_uri.indexOf('/') + 1), put_query.substring(put_query.indexOf('&')), String.valueOf(filesize), chunk_size, meta_upos_uri, biz_id);
        System.out.println(preUploadUrl);
        Map<String, String> header2 = new HashMap<>();
        header2.put("X-Upos-Auth", auth);
        String res3 = HttpClientUtil.post(preUploadUrl, "{}", header2);
        System.out.println(res3);
        JSONObject uploadObj = JSONObject.parseObject(res3);
        String upload_id = uploadObj.getString("upload_id");


        // 分割大小，这里设置为10MB

        int chunkNumber = 1;

        try {
            File inputFile = new File(inputVideoPath);
            FileInputStream fileInputStream = new FileInputStream(inputFile);
            byte[] buffer = new byte[Math.toIntExact(chunk_size)];

            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                String outputFilePath = outputDirectory + "chunk_" + chunkNumber + ".bin";
                File outputFile = new File(outputFilePath);
                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                fileOutputStream.write(buffer, 0, bytesRead);
                fileOutputStream.close();

                chunkNumber++;
            }
            chunkNumber--;

            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= chunkNumber; i++) {
            long end = i == chunkNumber ? filesize : i * chunk_size;
            long start = (i-1)*chunk_size;
            File file = new File(outputDirectory + "chunk_" + i +".bin");
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpPut httpPut = new HttpPut(String.format("https:%s%s?partNumber=%s&uploadId=%s&chunk=%s&chunks=%s&size=%s&start=%s&end=%s&total=%s",
                        endpoint,mp4_upos_uri.substring(mp4_upos_uri.indexOf('/') + 1),i,upload_id,i-1,chunkNumber, end - start,start, end,filesize)); // 替换为实际的目标URL

                byte[] binaryData = new byte[(int) file.length()];

                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(binaryData);
                fileInputStream.close();

                ByteArrayEntity entity = new ByteArrayEntity(binaryData);
                httpPut.setEntity(entity);
                httpPut.setHeader("Content-Type", "application/octet-stream");
                httpPut.setHeader("X-Upos-Auth",auth);

                CloseableHttpResponse response = httpClient.execute(httpPut);
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());
                if(statusCode==200){
                    System.out.println("上传中："+i*100/chunkNumber+"%");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JSONObject body = new JSONObject();
        JSONArray parts = new JSONArray();
        for(int i= 1;i<=chunkNumber;i++){
            JSONObject item = new JSONObject();
            item.put("partNumber",i);
            item.put("eTag","etag");
            parts.add(item);
        }
        body.put("parts",parts);
        System.out.println(body.toString());
        String res4 = HttpClientUtil.post(
                String.format(
                        "https:%s%s?output=json&name=test.mp4%s&uploadId=%s&biz_id=%s",
                        endpoint,mp4_upos_uri.substring(mp4_upos_uri.indexOf('/') + 1), put_query.substring(put_query.indexOf('&')),upload_id,biz_id
                ),body.toString(),header2
        );
        System.out.println(res4);
        JSONObject body1 = new JSONObject();
        JSONObject subtitle = new JSONObject();
        JSONArray videos = new JSONArray();
        subtitle.put("open",0);
        subtitle.put("lan","");
        JSONObject video = new JSONObject();
        video.put("filename",mp4_upos_uri.substring(mp4_upos_uri.lastIndexOf('/')+1,mp4_upos_uri.indexOf('.')));
        video.put("title","test");
        video.put("desc","");
        video.put("cid",biz_id);
        videos.add(video);
        body1.put("act_reserve_create",0);
        body1.put("copyright",1);
        body1.put("cover","https://i0.hdslb.com/bfs/archive/2e8c380c85c6970712ea8ede8b26cdc23f0a5b91.jpg");
        //TODO 封面上传
        body1.put("csrf",csrf);
        body1.put("desc","");
        body1.put("desc_format_id",0);
        body1.put("dolby",0);
        body1.put("dynamic","");
        body1.put("interactive",0);
        body1.put("lossless_music",0);
        body1.put("no_disturbance",0);
        body1.put("no_reprint",1);
        body1.put("recreate",-1);
        body1.put("tag","助眠,音乐");
        body1.put("tid",130);
        body1.put("title","test");
        body1.put("up_close_danmu",false);
        body1.put("up_close_reply",false);
        body1.put("up_selection_reply",false);
        body1.put("web_os",1);
        body1.put("subtitle",subtitle);
        body1.put("videos",videos);
        System.out.println(body1);
        String res5 = HttpClientUtil.post(String.format("https://member.bilibili.com/x/vu/web/add/v3?t=%s&csrf=%s", LocalTime.now(),csrf),body1.toString(),header);
        System.out.println(res5);
    }
}
