package com.example.Base.main.Service.Data.photo;

import java.util.List;

public class GoodVIew {
    private String code;
    private List<Good> data;
    private String message;

    public GoodVIew(String code, List<Good> data, String message) {
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

    public List<Good> getData() {
        return data;
    }

    public void setData(List<Good> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
