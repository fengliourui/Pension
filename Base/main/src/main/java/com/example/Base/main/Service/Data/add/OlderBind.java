package com.example.Base.main.Service.Data.add;

import java.util.List;

public class OlderBind {

        private String code;
        private List<OlderDTO> data;
        private String message;


    public OlderBind(String code, List<OlderDTO> data, String message) {
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

    public List<OlderDTO> getData() {
        return data;
    }

    public void setData(List<OlderDTO> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
