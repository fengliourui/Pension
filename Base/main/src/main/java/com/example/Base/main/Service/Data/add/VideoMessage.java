package com.example.Base.main.Service.Data.add;

public class VideoMessage {
    private String code;
    private Videodata data;
    private String message;

    public VideoMessage(String code, Videodata data, String message) {
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

    public Videodata getData() {
        return data;
    }

    public void setData(Videodata data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
