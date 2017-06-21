package com.example.tg.useotherlib.bean;

import java.io.Serializable;

/**
 * Created by tg on 2017/6/21.
 */

public class VideoTypeInfo extends Data implements Serializable{

    /**
     * airTime : 0
     * duration :
     * loadType : html
     * score : 0
     * angleIcon :
     * dataId :
     * description :
     * loadURL : http://h5.svipmovie.com/h5/transformers5/index.html
     * shareURL :
     * pic : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/17/1497656139937008319.jpg
     * title : 《变形金刚5：最后的骑士》限时送票
     * roomId :
     */

    private int airTime;
    private String duration;
    private String loadType;
    private int score;
    private String angleIcon;
    private String dataId;
    private String description;
    private String loadURL;
    private String shareURL;
    private String pic;
    private String title;
    private String roomId;

    public int getAirTime() {
        return airTime;
    }

    public void setAirTime(int airTime) {
        this.airTime = airTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAngleIcon() {
        return angleIcon;
    }

    public void setAngleIcon(String angleIcon) {
        this.angleIcon = angleIcon;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoadURL() {
        return loadURL;
    }

    public void setLoadURL(String loadURL) {
        this.loadURL = loadURL;
    }

    public String getShareURL() {
        return shareURL;
    }

    public void setShareURL(String shareURL) {
        this.shareURL = shareURL;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "ChildListBean{" +
                "airTime=" + airTime +
                ", duration='" + duration + '\'' +
                ", loadType='" + loadType + '\'' +
                ", score=" + score +
                ", angleIcon='" + angleIcon + '\'' +
                ", dataId='" + dataId + '\'' +
                ", description='" + description + '\'' +
                ", loadURL='" + loadURL + '\'' +
                ", shareURL='" + shareURL + '\'' +
                ", pic='" + pic + '\'' +
                ", title='" + title + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }

}
