package org.example.core.constpool;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/12 20:47
 */
public class ConstPool {

    //账号平台
    public enum AccountPlatForm{

        BILIBILI(1),
        DOUYIN(2);

        private int id;

        AccountPlatForm(int id){
            this.id = id;
        }

        public static int getPlatFormId (int id){
            return id;
        }
        public static String fromId(int id) {
            for (AccountPlatForm platform : values()) {
                if (platform.id == id) {
                    return platform.toString();
                }
            }
            throw new IllegalArgumentException("Invalid id: " + id);
        }
    }

}
