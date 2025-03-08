package com.example.d424_captstone_jv.ui.GigList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.d424_captstone_jv.Database.GigRepository;
import com.example.d424_captstone_jv.Entities.Gig;

import java.util.List;

public class GigListViewModel extends ViewModel {
    private final GigRepository gigRepository;
    private final MutableLiveData<String> searchQuery = new MutableLiveData<>("");
    private final MutableLiveData<Boolean> isCompletedFilter = new MutableLiveData<>(null);



    private final LiveData<List<Gig>> gigs;

    public GigListViewModel(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
        this.gigs = gigRepository.getAllGigs();
    }

    public LiveData<List<Gig>> getFilteredGigs() {
        return Transformations.switchMap(searchQuery, query -> {
            if (query == null || query.trim().isEmpty()) {
                return Transformations.switchMap(isCompletedFilter, isCompleted -> {
                    if (isCompleted == null) {
                        return gigRepository.getAllGigs();
                    } else if (isCompleted) {
                        return gigRepository.getCompletedGigs();
                    } else {
                        return gigRepository.getUpcomingGigs();
                    }
                });
            } else {
                return gigRepository.searchGigs(query);
            }
        });
    }

    public void setSearchQuery(String query) {
        searchQuery.setValue(query);
    }

    public void setFilter(Boolean isCompleted) {
        isCompletedFilter.setValue(isCompleted);
    }

    public LiveData<List<Gig>> getGigs() {
        return gigs;
    }
}

