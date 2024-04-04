package com.example.Base.main.Service.Data.add;

public class Numdeals {
    private String code;
    private NumDeal data;
    private String message;

    public Numdeals(String code, NumDeal data, String message) {
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

    public NumDeal getData() {
        return data;
    }

    public void setData(NumDeal data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
