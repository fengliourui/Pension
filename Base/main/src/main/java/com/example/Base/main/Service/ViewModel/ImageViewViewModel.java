package com.example.Base.main.Service.ViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.Base.main.Service.Data.Parameter.Three;
import com.example.Base.main.Service.Data.photo.Imageview;
import com.example.Base.main.Service.Repository;


public class ImageViewViewModel extends ViewModel  {
    public MutableLiveData<Three> locationLiveData = new MutableLiveData<>();
    public String tag;
    public String num;
    //登陆令牌
    public String auth;
    public LiveData<Imageview> ImageviewLiveData = Transformations.switchMap(locationLiveData, z1 -> {
        return  Repository.getImageLiveData(z1.getData1(),z1.getData2(),z1.getData3());
    });
    public void getImageView(String tag,String num,String auth) {
        this.tag =tag;
        this.num =num;
        this.auth =auth;
        Three z1 = new Three(tag,num,auth);
        locationLiveData.postValue(z1);
    }
}


