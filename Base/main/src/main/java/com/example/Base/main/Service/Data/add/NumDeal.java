package com.example.Base.main.Service.Data.add;

public class NumDeal {
    private  String ret;
    private  String ret_msg;

    public NumDeal(String ret, String ret_msg) {
        this.ret = ret;
        this.ret_msg = ret_msg;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }
}
