package com.example.nurse.ShowUi;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> returnedData = new MutableLiveData<>();

    public void setReturnedData(String a) {
        returnedData.setValue(a);
    }

    public LiveData<String> getReturnedData() {
        return returnedData;
    }
}
