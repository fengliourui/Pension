package com.example.Base.main.Service.Data.add;

public class nameAndIdentifyId {
    private String identifyId;
    private String realName;

    public nameAndIdentifyId(String identifyId, String realName) {
        this.identifyId = identifyId;
        this.realName = realName;
    }

    public String getIdentifyId() {
        return identifyId;
    }

    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
