package org.example.core.pojo;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/24 19:28
 */
public enum PlatformType {

    DOUYU(1),
    BILIBILI(2),
    DOUYIN(3);
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
