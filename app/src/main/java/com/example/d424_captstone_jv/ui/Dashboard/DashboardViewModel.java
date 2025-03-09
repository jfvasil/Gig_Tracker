package com.example.d424_captstone_jv.ui.Dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.d424_captstone_jv.Database.GigRepository;
import com.example.d424_captstone_jv.Entities.Gig;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DashboardViewModel extends ViewModel {

    private final GigRepository gigRepository;
    private final LiveData<List<Gig>> upcomingGigs;
    private final LiveData<Double> totalPayments;

    public DashboardViewModel(GigRepository gigRepository, int userId) {
        this.gigRepository = gigRepository;
        this.upcomingGigs = gigRepository.getUpcomingGigs(userId);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        String endDate = sdf.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        String startDate = sdf.format(calendar.getTime());

        this.totalPayments = gigRepository.getTotalPaymentsForMonth(startDate, endDate, userId);
    }

    public LiveData<List<Gig>> getUpcomingGigs() {
        return upcomingGigs;
    }

    public LiveData<Double> getTotalPayments() {
        return totalPayments;
    }
}
