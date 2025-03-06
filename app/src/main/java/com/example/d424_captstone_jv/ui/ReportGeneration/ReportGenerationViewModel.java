package com.example.d424_captstone_jv.ui.ReportGeneration;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReportGenerationViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ReportGenerationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Report Generation page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
