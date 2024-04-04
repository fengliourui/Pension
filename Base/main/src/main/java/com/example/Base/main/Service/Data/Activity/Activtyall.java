package com.example.Base.main.Service.Data.Activity;

import java.util.List;

public class Activtyall {
    private String code;
    private List<ActivityDTO> data;
    private String message;

    public Activtyall(String code, List<ActivityDTO> data, String message) {
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

    public List<ActivityDTO> getData() {
        return data;
    }

    public void setData(List<ActivityDTO> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
