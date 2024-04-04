package com.example.Base.main.Service.Data.Parameter;

import com.example.Base.main.Service.Data.add.nameAndIdentifyId;

public class BinOlder {
   private nameAndIdentifyId nameAndIdentifyId;
   private String auth;

    public BinOlder(com.example.Base.main.Service.Data.add.nameAndIdentifyId nameAndIdentifyId, String auth) {
        this.nameAndIdentifyId = nameAndIdentifyId;
        this.auth = auth;
    }

    public com.example.Base.main.Service.Data.add.nameAndIdentifyId getNameAndIdentifyId() {
        return nameAndIdentifyId;
    }

    public void setNameAndIdentifyId(com.example.Base.main.Service.Data.add.nameAndIdentifyId nameAndIdentifyId) {
        this.nameAndIdentifyId = nameAndIdentifyId;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
