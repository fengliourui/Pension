package com.example.Base.main.Service.Data.Parameter;


import com.example.Base.main.Service.Data.older.userAvatarVo;

public class Mss {
  private userAvatarVo data1;
  private   String data2;

    public Mss(userAvatarVo data1, String data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    public userAvatarVo getData1() {
        return data1;
    }

    public void setData1(userAvatarVo data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }
}
