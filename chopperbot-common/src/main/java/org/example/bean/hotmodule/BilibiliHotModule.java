package org.example.bean.hotmodule;

import lombok.Data;
import org.example.bean.HotModule;
import org.example.bean.Live;

/**
 * @author Genius
 * @date 2023/07/18 20:49
 **/
@Data
public class BilibiliHotModule extends HotModule<Live> {

    private String parent_id;

    private String parent_name;

    private String act_id;

    private String pic;

    private int area_type;

    public BilibiliHotModule(String tagId, String tagName, String parent_id, String parent_name,
                             String act_id, String pic, int area_type) {
        super(tagId, tagName);
        this.parent_id = parent_id;
        this.parent_name = parent_name;
        this.act_id = act_id;
        this.pic = pic;
        this.area_type = area_type;
    }
}
