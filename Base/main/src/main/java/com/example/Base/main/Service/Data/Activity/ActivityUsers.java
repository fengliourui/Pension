package com.example.Base.main.Service.Data.Activity;

import java.util.List;

public class ActivityUsers {
    List<ActivtyUser> users;

    public List<ActivtyUser> getUsers() {
        return users;
    }

    public ActivityUsers(List<ActivtyUser> users) {
        this.users = users;
    }

    public void setUsers(List<ActivtyUser> users) {
        this.users = users;
    }
}
