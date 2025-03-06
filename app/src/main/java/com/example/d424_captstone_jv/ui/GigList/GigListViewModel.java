package com.example.d424_captstone_jv.ui.GigList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GigListViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GigListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Gig List page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
