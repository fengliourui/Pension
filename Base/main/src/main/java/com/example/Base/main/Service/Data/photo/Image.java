package com.example.Base.main.Service.Data.photo;

import java.util.List;

public class Image {
    private String code;//:响应结果
    private List<OssFileDTO> data;//视频的集合
    private String message;//错误信息

    public Image(String code, List<OssFileDTO> data, String message) {
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

    public List<OssFileDTO> getData() {
        return data;
    }

    public void setData(List<OssFileDTO> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
