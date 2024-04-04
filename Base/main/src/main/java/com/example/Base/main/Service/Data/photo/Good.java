package com.example.Base.main.Service.Data.photo;

public class Good {
    private  String VideoId;
    private  String VideoUrl;

    public Good(String videoId, String videoUrl) {
        VideoId = videoId;
        VideoUrl = videoUrl;
    }

    public String getVideoId() {
        return VideoId;
    }

    public void setVideoId(String videoId) {
        VideoId = videoId;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }
}
