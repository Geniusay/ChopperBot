package org.example.bean.hotmodule;

import lombok.Data;
import org.example.bean.HotModule;
import org.example.bean.Live;

/**
 * @author Genius
 * @date 2023/07/18 20:49
 **/

public class BilibiliHotModule extends HotModule<Live> {

    private BilibiliParentHotModule parentHotModule;

    private String pinyin;

    private String act_id;

    private int hot_status;

    private int pk_status;

    private int lock_status;

    private String pic;

    private int area_type;

    public BilibiliHotModule(String tagId, String tagName, BilibiliParentHotModule parentHotModule, String pinyin,
                             String act_id, int hot_status, int pk_status, int lock_status, String pic, int area_type) {
        super(tagId, tagName);
        this.parentHotModule = parentHotModule;
        this.pinyin = pinyin;
        this.act_id = act_id;
        this.hot_status = hot_status;
        this.pk_status = pk_status;
        this.lock_status = lock_status;
        this.pic = pic;
        this.area_type = area_type;
    }

    public BilibiliParentHotModule getParentHotModule() {
        return parentHotModule;
    }
    public String getPinyin() {
        return pinyin;
    }
    public String getAct_id() {
        return act_id;
    }
    public int getHot_status() {
        return hot_status;
    }
    public int getPk_status() {
        return pk_status;
    }
    public int getLock_status() {
        return lock_status;
    }
    public String getPic() {
        return pic;
    }
    public int getArea_type() {
        return area_type;
    }

    public void setParentHotModule(BilibiliParentHotModule parentHotModule) {
        this.parentHotModule = parentHotModule;
    }
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    public void setAct_id(String act_id) {
        this.act_id = act_id;
    }
    public void setHot_status(int hot_status) {
        this.hot_status = hot_status;
    }
    public void setPk_status(int pk_status) {
        this.pk_status = pk_status;
    }
    public void setLock_status(int lock_status) {
        this.lock_status = lock_status;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public void setArea_type(int area_type) {
        this.area_type = area_type;
    }
}
