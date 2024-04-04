package com.example.module_manage.util;
import android.media.Image;
import android.provider.MediaStore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 这个类是firstpage里的listview将活动展示出来时
 * 网络请求后需要将得到的结果解析出来
 * 没什么好甚久的就是set get方法 tostring方法
 */

public class ActivityEvent {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;

    @SerializedName("introduce")
    private String introduce;

    @SerializedName("images")
    private List<Image> images;

    @SerializedName("videos")
    private List<Video> videos;

    @SerializedName("curParticipants")
    private int curParticipants;

    @SerializedName("maxParticipants")
    private int maxParticipants;

    @SerializedName("applyStart")
    private String applyStart;

    @SerializedName("applyEnd")
    private String applyEnd;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("endTime")
    private String endTime;

    @SerializedName("status")
    private String status;

    //Image类来映射images中的对象
    public static class Image{
        @SerializedName("id")
        private String id;

        @SerializedName("url")
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        @Override
        public String toString() {
            return "Image{" +
                    "id='" + id + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    //video类来映射videos数组中的对象
    public static class Video{
        @SerializedName("id")
        private String id;

        @SerializedName("url")
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        @Override
        public String toString() {
            return "Video{" +
                    "id='" + id + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public int getCurParticipants() {
        return curParticipants;
    }

    public void setCurParticipants(int curParticipants) {
        this.curParticipants = curParticipants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getApplyStart() {
        return applyStart;
    }

    public void setApplyStart(String applyStart) {
        this.applyStart = applyStart;
    }

    public String getApplyEnd() {
        return applyEnd;
    }

    public void setApplyEnd(String applyEnd) {
        this.applyEnd = applyEnd;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", introduce='" + introduce + '\'' +
                ", curParticipants=" + curParticipants +
                ", maxParticipants=" + maxParticipants +
                ", applyStart='" + applyStart + '\'' +
                ", applyEnd='" + applyEnd + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                ", images=" + images +
                ", videos=" + videos +
                '}';
    }
}
