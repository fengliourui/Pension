package com.example.Base.main.Service.Data.Activity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActivtyUser {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("introduce")
    private String introduce;
    @SerializedName("images")
    private List<ImagesDTO> images;
    @SerializedName("videos")
    private List<?> videos;
    @SerializedName("curParticipants")
    private Integer curParticipants;
    @SerializedName("maxParticipants")
    private Integer maxParticipants;
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

    public List<ImagesDTO> getImages() {
        return images;
    }

    public void setImages(List<ImagesDTO> images) {
        this.images = images;
    }

    public List<?> getVideos() {
        return videos;
    }

    public void setVideos(List<?> videos) {
        this.videos = videos;
    }

    public Integer getCurParticipants() {
        return curParticipants;
    }

    public void setCurParticipants(Integer curParticipants) {
        this.curParticipants = curParticipants;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
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

    public static class ImagesDTO {
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
    }
}
