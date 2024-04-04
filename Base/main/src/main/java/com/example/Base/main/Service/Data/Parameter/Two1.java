package com.example.Base.main.Service.Data.Parameter;

import java.io.File;

public class Two1 {
    private File file;
    private String auth;

    public Two1(File file, String auth) {
        this.file = file;
        this.auth = auth;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
