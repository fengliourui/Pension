package com.example.Base.main.Service.Data.older;

public class Message {
    String code;
    Mess data;//
    String message;

    public Message(String code, Mess data, String message) {
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

    public Mess getData() {
        return data;
    }

    public void setData(Mess data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
