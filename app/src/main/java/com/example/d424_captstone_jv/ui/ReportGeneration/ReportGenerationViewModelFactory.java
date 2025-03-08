package com.example.d424_captstone_jv.ui.ReportGeneration;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.d424_captstone_jv.Database.GigRepository;

public class ReportGenerationViewModelFactory implements ViewModelProvider.Factory {
    private final GigRepository gigRepository;

    public ReportGenerationViewModelFactory(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ReportGenerationViewModel.class)) {
            return (T) new ReportGenerationViewModel(gigRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}