package com.example.d424_captstone_jv.ui.AddGig;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddGigViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddGigViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Add Gig page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
