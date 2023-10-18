package org.example.pojo;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/24 19:28
 */
public enum PlatformType {

    BILIBILI(1),
    DOUYIN(2);
    private final int index;

    public int getId() {
        return index;
    }

    public static PlatformType getPlatform(int id) {
        for (PlatformType platform : values()) {
            if (platform.index == id) {
                return platform;
            }
        }
        throw new IllegalArgumentException("Invalid platform id: " + id);
    }

    PlatformType(int index){
        this.index = index;
    }

}
