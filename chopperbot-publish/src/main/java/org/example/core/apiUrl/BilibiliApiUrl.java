package org.example.core.apiUrl;

/**
 * @author dhx
 * @date 2023/9/25 21:01
 */
public class BilibiliApiUrl {
    public final static String VIDEO_PREUPLOAD_URL = "https://member.bilibili.com/preupload?probe_version=20221109&upcdn=bda2&zone=cs&name=test.mp4&r=upos&profile=ugcfx%%2Fbup&ssl=0&version=2.14.0.0&build=2140000&size=%s&webVersion=2.14.0";

    public final static String TXT_PREUPLOAD_URL= "https://member.bilibili.com/preupload?name=file_meta.txt&size=2000&r=upos&profile=fxmeta%2Fbup&ssl=0&version=2.14.0.0&build=2140000&webVersion=2.14.0";

    public final static String PREUPLOAD_URL = "https:%s%s?uploads&output=json%s&filesize=%s&partsize=%s&meta_upos_uri=%s&biz_id=%s";

    public final static String VIDEO_UPLOAD_URL = "https:%s%s?partNumber=%s&uploadId=%s&chunk=%s&chunks=%s&size=%s&start=%s&end=%s&total=%s";

    public final static String UPLOAD_FINISHI_URL = "https:%s%s?output=json&name=test.mp4%s&uploadId=%s&biz_id=%s";

    public final static String COVER_UPLOAD_URL = "https://member.bilibili.com/x/vu/web/cover/up?t=";

    public final static String SEND_MANUSCRIPT_URL = "https://member.bilibili.com/x/vu/web/add/v3?t=%s&csrf=%s";
}
