package org.example.constpool;

import java.util.List;

/**
 * @author Genius
 * @date 2023/04/21 03:03
 **/
//常量池
public class ConstPool {

    /**模块名**/
    public static final String FILE = "File";
    public static final String ACCOUNT = "Account";
    public static final String SECTION = "Section";
    public static final String BARRAGE = "Barrage";
    public static final String CREEPER = "Creeper";
    public static final String SECTION_WORK = "SectionWork";
    public static final String HOT = "Hot";
    public static final String PUBLISH = "Publish";

    /**其他**/
    public static final String NULL_TIME = "nil";

    /**图片格式**/
    public static final List<String> PIC_TYPES = List.of("jpg","jpeg","png","svg");

    /**直播平台**/

    public enum PLATFORM{
        DOUYU("douyu"),
        HUYA("huya"),
        BILIBILI("bilibili"),
        DOUYING("douyin"),
        TIKTOK("tiktok"),
        TWITCH("twitch");
        private final String name;
        PLATFORM(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }

    }
    public static final String DOUYU = "douyu";

    public static final String HUYA = "huya";
    public static final String BILIBILI = "bilibili";

    public static final String TIKTOK = "douyin";

    public static final String TWITCH = "twitch";
}
