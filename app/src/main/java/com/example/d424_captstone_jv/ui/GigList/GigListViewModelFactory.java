package com.example.d424_captstone_jv.ui.GigList;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.d424_captstone_jv.Database.GigRepository;

public class GigListViewModelFactory implements ViewModelProvider.Factory {
    private final GigRepository gigRepository;

    private final int userId;

    public GigListViewModelFactory(GigRepository gigRepository, int userId) {
        this.gigRepository = gigRepository;
        this.userId = userId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GigListViewModel.class)) {
            return (T) new GigListViewModel(gigRepository, userId);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
