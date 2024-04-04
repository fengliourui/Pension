package com.example.Base.main.Service.Data.add;

public class Commentlikes {
    private String code;
    private CommentLike data;
    private String message;

    public Commentlikes(String code, CommentLike data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CommentLike getData() {
        return data;
    }

    public void setData(CommentLike data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
