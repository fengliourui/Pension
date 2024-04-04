package com.example.Base.main.Service.Data.add;

public class OlderDTO {
    private String olderId;
    private String olderName;
    private String avatarUrl;

    public OlderDTO(String olderId, String olderName, String avatarUrl) {
        this.olderId = olderId;
        this.olderName = olderName;
        this.avatarUrl = avatarUrl;
    }

    public String getOlderId() {
        return olderId;
    }

    public void setOlderId(String olderId) {
        this.olderId = olderId;
    }

    public String getOlderName() {
        return olderName;
    }

    public void setOlderName(String olderName) {
        this.olderName = olderName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
