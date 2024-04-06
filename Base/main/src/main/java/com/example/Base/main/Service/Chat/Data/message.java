package com.example.Base.main.Service.Chat.Data;

public class message {
    private String role;//身份

    private String content;//消息

    public message() {
    }

    public message(int times, String content) {
        if (times % 2 == 1){
            this.role = "user";
        }else{
            this.role = "assistant";
        }

        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
