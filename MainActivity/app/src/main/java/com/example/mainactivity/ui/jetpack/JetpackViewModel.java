package com.example.mainactivity.ui.jetpack;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

public class JetpackViewModel extends ViewModel implements Observable {
    private MutableLiveData<String> data;
    private MutableLiveData<User> userMutableLiveData;
    public String stringValue = "Hello Jetpack";
    private PropertyChangeRegistry callBacks = new PropertyChangeRegistry();

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

    ///implement Observable interface
    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callBacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callBacks.remove(callback);
    }

    //     * Notifies observers that all properties of this instance have changed.
    void notifyChange() {
        callBacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    void notifyPropertyChanged(int fieldID) {
        callBacks.notifyCallbacks(this, fieldID, null);
    }
}
