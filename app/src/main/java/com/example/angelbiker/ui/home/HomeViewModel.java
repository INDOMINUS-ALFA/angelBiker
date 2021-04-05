package com.example.angelbiker.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Hi User!" + "\n"+
                "Thank you for downloading the app I hope you enjoy it "
               );
    }

    public LiveData<String> getText() {
        return mText;
    }
}