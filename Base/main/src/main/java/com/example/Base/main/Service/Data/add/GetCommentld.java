package com.example.Base.main.Service.Data.add;

import java.util.List;

public class GetCommentld {
    private String code;
    private List<CommentDto> data;
    private String message;

    public GetCommentld(String code, List<CommentDto> data, String message) {
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

    public List<CommentDto> getData() {
        return data;
    }

    public void setData(List<CommentDto> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
