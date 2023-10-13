package org.example.pojo;

import java.util.List;

/**
 * B站视频稿件实体类
 * @author dhx
 * @date 2023/9/24 20:57
 */
public class BilibiliVideoManuscript extends VideoManuscript {
    //视频分区
    private int tid;

    //视频标签
    private List<String> tag;

    //关闭弹幕
    private boolean close_danmu = false;

    //关闭评论
    private boolean close_reply = false;

    //精选评论
    private boolean selection_reply = false;

    public BilibiliVideoManuscript(String title, String desc, int tid, List<String> tag) {
        super(title, desc);
        this.tid = tid;
        this.tag = tag;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public boolean isClose_danmu() {
        return close_danmu;
    }

    public void setClose_danmu(boolean close_danmu) {
        this.close_danmu = close_danmu;
    }

    public boolean isClose_reply() {
        return close_reply;
    }

    public void setClose_reply(boolean close_reply) {
        this.close_reply = close_reply;
    }

    public boolean isSelection_reply() {
        return selection_reply;
    }

    public void setSelection_reply(boolean selection_reply) {
        this.selection_reply = selection_reply;
    }
}
