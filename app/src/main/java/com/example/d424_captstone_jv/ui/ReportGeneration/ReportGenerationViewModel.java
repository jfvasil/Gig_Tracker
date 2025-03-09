package com.example.d424_captstone_jv.ui.ReportGeneration;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d424_captstone_jv.Database.GigRepository;
import com.example.d424_captstone_jv.Entities.Gig;

import java.util.Date;
import java.util.List;

public class ReportGenerationViewModel extends ViewModel {
    private final GigRepository gigRepository;

    private final MutableLiveData<Date> startDate = new MutableLiveData<>();

    private final MutableLiveData<Date> endDate = new MutableLiveData<>();
    private final int userId;

    public ReportGenerationViewModel(GigRepository gigRepository, int userId) {
        super();
        this.gigRepository = gigRepository;
        this.userId = userId;
    }

    public void setStartDate(Date date) {
        this.startDate.setValue(date);
    }

    public void setEndDate(Date date) {
        this.endDate.setValue(date);
    }

    public LiveData<Date> getStartDate() {
        return startDate;
    }

    public LiveData<Date> getEndDate() {
        return endDate;
    }

    public LiveData<List<Gig>> getGigsInDateRange(String startDate, String endDate, int userid  ) {
        return gigRepository.getGigsInDateRange(startDate, endDate, userid);
    }
}
