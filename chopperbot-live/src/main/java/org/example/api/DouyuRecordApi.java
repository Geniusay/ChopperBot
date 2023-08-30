package org.example.api;

import org.example.core.component.M3U8Handle;
import org.example.core.component.SimpleRequest;
import org.example.core.creeper.loadconfig.DouyuRecordConfig;
import org.example.core.parser.impl.DouyuM3u8UrlParser;
import org.example.pojo.record.RecordList;

/**
 * 斗鱼录播api
 * @author 燧枫
 * @date 2023/8/1 21:53
 */
public class DouyuRecordApi {

    // 根据主播id首次获取录播列表(默认为第一页，每页数量为20)
    public static RecordList getRecordList(String upId) {
        return getRecordList(upId, 1);
    }

    // 指定页数获取录播列表
    public static RecordList getRecordList(String upId, int index) {
        return SimpleRequest.getRecordList(upId, index);
    }

    // 根据vid获取一场录播的M3U8下载链接
    public static String getM3U8ByVid(String vid) {
        return new DouyuM3u8UrlParser().getUrl(new DouyuRecordConfig("yourPath","fileName",vid,0));
    }

    // 根据M3U8链接,开始时间,结束时间,保存位置,文件名下载一个录播视频
    public static void downloadVideoByM3U8(String url, String startTime, String endTime, String downloadLocation, String outputFileName) {
        M3U8Handle.downloadAndCutVideo(url, startTime, endTime, downloadLocation, outputFileName);
    }

    public static void main(String[] args) {
//        String vid = "wLjGvLxr8yPvmO90";
//        String url = getM3U8ByVid(vid);
//        downloadVideoByM3U8(url, "00:00:00", "00:10:00", "D://", "test.mp4");
         System.out.println(getRecordList("1VwKmGb5XA4l"));
    }
}
