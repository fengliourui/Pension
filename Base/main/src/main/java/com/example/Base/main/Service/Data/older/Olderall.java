package com.example.Base.main.Service.Data.older;

public class Olderall {
    private String code;
    private Older data;
    private String message;

    public Olderall(String code, Older data, String message) {
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

    public Older getData() {
        return data;
    }

    public void setData(Older data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
