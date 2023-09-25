package org.example.core.publisher.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.example.core.apiUrl.BilibiliApiUrl;
import org.example.core.publisher.PlatformVideoPublisher;
import org.example.exception.ChopperBotException;
import org.example.pojo.BilibiliUploader;

import org.example.utils.CookieUtil;
import org.example.utils.FileUtil;
import org.example.utils.HttpClientUtil;
import org.example.utils.VideoDeviceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author dhx
 * @date 2023/9/18 20:49
 */
public class BilibiliVideoPublisher implements PlatformVideoPublisher<BilibiliUploader> {
    @Override
    public void publishVideo(BilibiliUploader bilibiliUploader) {
        Map<String, String> header = bilibiliUploader.getHeader();
        String videoPath = bilibiliUploader.getVideoPath();
        String devicePath = bilibiliUploader.getDeviceVideoPath();
        String cookie = bilibiliUploader.getCookie();
        String coverPath = bilibiliUploader.getCoverPath();
        String csrf = CookieUtil.getParam(cookie,"bili_jct");
        if(csrf == null){
            throw new ChopperBotException("Cookie错误");
        }
        int filesize = FileUtil.getFilesize(videoPath);
        if (filesize == -1) {
            throw new ChopperBotException("获取文件大小失败");
        }

        //申请上传
        String res = HttpClientUtil.get(String.format(BilibiliApiUrl.VIDEO_PREUPLOAD_URL,filesize)
                , header);
        JSONObject mp4Obj = JSONObject.parseObject(res);
        String mp4_upos_uri = mp4Obj.getString("upos_uri");
        String put_query = mp4Obj.getString("put_query");
        String endpoint = mp4Obj.getString("endpoint");
        Long chunk_size = mp4Obj.getLong("chunk_size");
        Long biz_id = mp4Obj.getLong("biz_id");
        String auth = mp4Obj.getString("auth");


        String res2 = HttpClientUtil.get(BilibiliApiUrl.TXT_PREUPLOAD_URL, header);
        JSONObject txtObj = JSONObject.parseObject(res2);
        String meta_upos_uri = txtObj.getString("upos_uri");
        String preUploadUrl = String.format(BilibiliApiUrl.PREUPLOAD_URL, endpoint, mp4_upos_uri.substring(mp4_upos_uri.indexOf('/') + 1), put_query.substring(put_query.indexOf('&')), String.valueOf(filesize), chunk_size, meta_upos_uri, biz_id);
        Map<String, String> header2 = new HashMap<>();
        header2.put("X-Upos-Auth", auth);
        String res3 = HttpClientUtil.post(preUploadUrl, "{}", header2);
        JSONObject uploadObj = JSONObject.parseObject(res3);
        String upload_id = uploadObj.getString("upload_id");

        //视频分片
        int chunkNumber = VideoDeviceUtil.device(videoPath, devicePath, chunk_size);
        if (chunkNumber != -1) {
            chunkNumber--;
        } else {
            throw new ChopperBotException("分割视频失败");
        }

        //上传视频
        for (int i = 1; i <= chunkNumber; i++) {
            long end = i == chunkNumber ? filesize : i * chunk_size;
            long start = (i - 1) * chunk_size;
            File file = new File(devicePath + "chunk_" + i + ".bin");
            if (!file.exists()) {
                throw new ChopperBotException(String.format("未找到视频片段%s", i));
            }
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpPut httpPut = new HttpPut(String.format(BilibiliApiUrl.VIDEO_UPLOAD_URL,
                        endpoint, mp4_upos_uri.substring(mp4_upos_uri.indexOf('/') + 1), i, upload_id, i - 1, chunkNumber, end - start, start, end, filesize)); // 替换为实际的目标URL

                byte[] binaryData = new byte[(int) file.length()];
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(binaryData);
                fileInputStream.close();

                ByteArrayEntity entity = new ByteArrayEntity(binaryData);
                httpPut.setEntity(entity);
                httpPut.setHeader("Content-Type", "application/octet-stream");
                httpPut.setHeader("X-Upos-Auth", auth);

                CloseableHttpResponse response = httpClient.execute(httpPut);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    System.out.println("上传中：" + i * 100 / chunkNumber + "%");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        VideoDeviceUtil.deleteChunks(devicePath);

        //通知上传完成
        JSONObject body = new JSONObject();
        JSONArray parts = new JSONArray();
        for (int i = 1; i <= chunkNumber; i++) {
            JSONObject item = new JSONObject();
            item.put("partNumber", i);
            item.put("eTag", "etag");
            parts.add(item);
        }
        body.put("parts", parts);
        String res4 = HttpClientUtil.post(
                String.format(
                        BilibiliApiUrl.UPLOAD_FINISHI_URL,
                        endpoint, mp4_upos_uri.substring(mp4_upos_uri.indexOf('/') + 1), put_query.substring(put_query.indexOf('&')), upload_id, biz_id
                ), body.toString(), header2
        );

        //上传封面
        String base64Image = "";
        try {
            File imageFile = new File(coverPath);
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            byte[] imageData = new byte[(int) imageFile.length()];
            fileInputStream.read(imageData);
            fileInputStream.close();
            base64Image = Base64.getEncoder().encodeToString(imageData);
            base64Image = "data:image/jpeg;base64," + base64Image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("cover", base64Image));
        list.add(new BasicNameValuePair("csrf", csrf));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, UTF_8);
        String res5 = HttpClientUtil.post(BilibiliApiUrl.COVER_UPLOAD_URL + LocalTime.now(), urlEncodedFormEntity, header);
        JSONObject cover = JSONObject.parseObject(res5);
        JSONObject coverData = cover.getJSONObject("data");
        if (coverData == null) return;
        String coverUrl = coverData.getString("url");

        //发布视频
        JSONObject body1 = new JSONObject();
        JSONObject subtitle = new JSONObject();
        JSONArray videos = new JSONArray();
        subtitle.put("open", 0);
        subtitle.put("lan", "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ");
        JSONObject param_video = new JSONObject();
        param_video.put("filename", mp4_upos_uri.substring(mp4_upos_uri.lastIndexOf('/') + 1, mp4_upos_uri.indexOf('.')));
        param_video.put("title", "test");
        param_video.put("desc", "");
        param_video.put("cid", biz_id);
        videos.add(param_video);
        body1.put("act_reserve_create", 0);
        body1.put("copyright", 1);
        body1.put("cover", coverUrl);   //封面
        body1.put("csrf", csrf);
        body1.put("desc", "");
        body1.put("desc_format_id", 0);
        body1.put("dolby", 0);
        body1.put("dynamic", "");
        body1.put("interactive", 0);
        body1.put("lossless_music", 0);
        body1.put("no_disturbance", 0);
        body1.put("no_reprint", 1);
        body1.put("recreate", -1);
        body1.put("tag", "助眠,音乐");  //视频标签
        body1.put("tid", 130);  //视频分区 130：音乐综合
        body1.put("title", "test");
        body1.put("up_close_danmu", false);
        body1.put("up_close_reply", false);
        body1.put("up_selection_reply", false);
        body1.put("web_os", 1);
        body1.put("subtitle", subtitle);
        body1.put("videos", videos);
        String res6 = HttpClientUtil.post(String.format(BilibiliApiUrl.SEND_MANUSCRIPT_URL, LocalTime.now(), csrf), body1.toString(), header);
        JSONObject res6Obj = JSONObject.parseObject(res6);
        int code = res6Obj.getInteger("code");
        if (code == 0) {
            System.out.println("发布成功");
        } else {
            System.out.println(res6);
            throw new ChopperBotException("发布失败，请手动发布");
        }
    }
}
