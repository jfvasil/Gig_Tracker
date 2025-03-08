package com.example.d424_captstone_jv.ui.Dashboard;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.d424_captstone_jv.Database.GigRepository;

public class DashboardViewModelFactory implements ViewModelProvider.Factory {
    private final GigRepository gigRepository;

    public DashboardViewModelFactory(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DashboardViewModel.class)) {
            return (T) new DashboardViewModel(gigRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
