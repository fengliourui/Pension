package com.example.Base.main.Service.Data.add;

public class CommentDto {
        private int id;
        private String nickname;
        private String  content;
        private int  likes;
        private String   is_like;
        private String  url;
        private int  is_owner;

    public CommentDto(int id, String nickname, String content, int likes, String is_like, String url, int is_owner) {
        this.id = id;
        this.nickname = nickname;
        this.content = content;
        this.likes = likes;
        this.is_like = is_like;
        this.url = url;
        this.is_owner = is_owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getIs_like() {
        return is_like;
    }

    public void setIs_like(String is_like) {
        this.is_like = is_like;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIs_owner() {
        return is_owner;
    }

    public void setIs_owner(int is_owner) {
        this.is_owner = is_owner;
    }
}
