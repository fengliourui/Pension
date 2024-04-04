package com.example.Base.main.Service.Data.Activity;

public class ActivityOne {
    private String code;
    private ActivityDTO data;
    private String message;

    public ActivityOne(String code, ActivityDTO data, String message) {
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

    public ActivityDTO getData() {
        return data;
    }

    public void setData(ActivityDTO data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
