package com.example.Base.main.Service.Data.Parameter;


import com.example.Base.main.Service.Data.add.publishCommentVo;

public class Comm {
  private publishCommentVo data1;
  private   String data2;

    public Comm(publishCommentVo data1, String data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    public publishCommentVo getData1() {
        return data1;
    }

    public void setData1(publishCommentVo data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }
}
