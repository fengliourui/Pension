package com.example.Base.main.Service.Data.add;

public class Videodata {
    private String url;
    private String like_num;
    private String comment_num;
    private String collect_num;
    private String is_collect;
    private String is_like;


    public Videodata(String url, String like_num, String comment_num, String collect_num, String is_collect, String is_like) {
        this.url = url;
        this.like_num = like_num;
        this.comment_num = comment_num;
        this.collect_num = collect_num;
        this.is_collect = is_collect;
        this.is_like = is_like;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(String collect_num) {
        this.collect_num = collect_num;
    }

    public String getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(String is_collect) {
        this.is_collect = is_collect;
    }

    public String getIs_like() {
        return is_like;
    }

    public void setIs_like(String is_like) {
        this.is_like = is_like;
    }
}
