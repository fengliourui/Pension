package com.example.Base.main.Service.Data.Activity;

import java.util.List;

public class ActivityDTO {
    private String applyEnd;
    private String applyStart;
    private int curParticipants;
    private String endTime;
    private String id;
    private List<Images> images;
    private String introduce;
    private int maxParticipants;
    private String startTime;
    private String title;
    private List<Video> videos;


    public ActivityDTO(String applyEnd, String applyStart, int curParticipants, String endTime, String id, List<Images> images, String introduce, int maxParticipants, String startTime, String title, List<Video> videos) {
        this.applyEnd = applyEnd;
        this.applyStart = applyStart;
        this.curParticipants = curParticipants;
        this.endTime = endTime;
        this.id = id;
        this.images = images;
        this.introduce = introduce;
        this.maxParticipants = maxParticipants;
        this.startTime = startTime;
        this.title = title;
        this.videos = videos;
    }

    public String getApplyEnd() {
        return applyEnd;
    }

    public void setApplyEnd(String applyEnd) {
        this.applyEnd = applyEnd;
    }

    public String getApplyStart() {
        return applyStart;
    }

    public void setApplyStart(String applyStart) {
        this.applyStart = applyStart;
    }

    public int getCurParticipants() {
        return curParticipants;
    }

    public void setCurParticipants(int curParticipants) {
        this.curParticipants = curParticipants;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
