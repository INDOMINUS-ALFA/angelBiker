package com.example.angelbiker.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Hi User!" + "\n"+
                "Remember If you drive DONT use phone make the roads a safe place"
               );
    }

    public LiveData<String> getText() {
        return mText;
    }
}