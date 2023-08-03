package org.example.pojo;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/8/3 21:04
 */
public class CreepBarrage {

    private String anchorName;
    private String roomId;
    private List<Barrage> barrageList;
    private String creeperTime;

    public String getAnchorName() {
        return anchorName;
    }

    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<Barrage> getBarrageList() {
        return barrageList;
    }

    public void setBarrageList(List<Barrage> barrageList) {
        this.barrageList = barrageList;
    }

    public String getCreeperTime() {
        return creeperTime;
    }

    public void setCreeperTime(String creeperTime) {
        this.creeperTime = creeperTime;
    }
}
