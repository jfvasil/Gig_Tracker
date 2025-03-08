package com.example.d424_captstone_jv.ui.GigList;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.d424_captstone_jv.Database.GigRepository;

public class GigListViewModelFactory implements ViewModelProvider.Factory {
    private final GigRepository gigRepository;

    public GigListViewModelFactory(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GigListViewModel.class)) {
            return (T) new GigListViewModel(gigRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
