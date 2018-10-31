package com.example.mainactivity.ui.jetpack;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class JetpackViewModel extends ViewModel {
    private MutableLiveData<String> data;
    private MutableLiveData<User> userMutableLiveData;
    public String stringValue = "Hello Jetpack";

    public LiveData<User> getUserLiveData() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData();
        }
        return userMutableLiveData;
    }

    public LiveData<User> getUserLiveData(String firstName) {
        User user = new User(firstName, "Sp");
        MutableLiveData<User> liveData = new MutableLiveData();
        liveData.postValue(user);
        return liveData;
    }

    public LiveData<String> getStringData() {
        if (data == null) {
            data = new MutableLiveData();
        }
        return data;
    }

    public void updateDataValue(String value) {
        data.postValue(value);
    }

}
