package org.example.core.pojo;

import lombok.Data;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/13 19:02
 */

@Data
public class VideoToPublish {

    private String videoPath;
    private String cookies;
    private PlatformType platform;
    private String coverPath;
    private String devicePath;
    private String tag;
    private String title;
    private String type;

    private VideoToPublish(Buider buider){
        this.videoPath = buider.videoPath;
        this.cookies = buider.cookies;
        this.coverPath = buider.coverPath;
        this.devicePath = buider.devicePath;
        this.tag = buider.tag;
        this.title = buider.title;
        this.type = buider.type;
    }

    public static class Buider{
        private String videoPath;
        private String cookies;
        private PlatformType platform;
        private String coverPath;
        private String devicePath;
        private String tag;
        private String title;
        private String type;

        public Buider(String videoPath,String cookies,int platform){
            this.videoPath = videoPath;
            this.cookies = cookies;
            this.platform = PlatformType.getPlatform(platform);
        }

        public Buider setCoverPath(String coverPath) {
            this.coverPath = coverPath;
            return this;
        }

        public Buider setDevicePath(String devicePath) {
            this.devicePath = devicePath;
            return this;
        }

        public Buider setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Buider setTitle(String title) {
            this.title = title;
            return this;
        }

        public Buider setType(String type) {
            this.type = type;
            return this;
        }

        public String getVideoPath() {
            return videoPath;
        }

        public String getCookies() {
            return cookies;
        }

        public String getCoverPath() {
            return coverPath;
        }

        public String getDevicePath() {
            return devicePath;
        }

        public String getTag() {
            return tag;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public VideoToPublish build(){
            return new VideoToPublish(this);
        }

    }

}
