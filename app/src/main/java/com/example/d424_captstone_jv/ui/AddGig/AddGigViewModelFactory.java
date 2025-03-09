package com.example.d424_captstone_jv.ui.AddGig;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.d424_captstone_jv.Database.GigRepository;

public class AddGigViewModelFactory implements ViewModelProvider.Factory {
    private final GigRepository gigRepository;

    public AddGigViewModelFactory(Application application) {
        this.gigRepository = new GigRepository(application);
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AddGigViewModel.class)) {
            return (T) new AddGigViewModel(gigRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class!");
    }
}
