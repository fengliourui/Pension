package com.example.Base.main.Service.Data.older;

public class Postavaterphoto {
    private String code;
    private data data;
    private String message;

    public Postavaterphoto(String code, Postavaterphoto.data data, String message) {
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

    public Postavaterphoto.data getData() {
        return data;
    }

    public void setData(Postavaterphoto.data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class data {
        private String id;
        private String url;

        public data(String id, String url) {
            this.id = id;
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
