package com.example.Base.main.Service.Data.nusion;

import com.google.gson.annotations.SerializedName;

public class Nurse {

    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private String data;
    @SerializedName("message")
    private String message;

    public Nurse(String code, String data, String message) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
