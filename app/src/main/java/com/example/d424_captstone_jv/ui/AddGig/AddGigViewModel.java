package com.example.d424_captstone_jv.ui.AddGig;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d424_captstone_jv.Database.GigRepository;
import com.example.d424_captstone_jv.Entities.Gig;
import com.example.d424_captstone_jv.ui.SessionManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddGigViewModel extends ViewModel {
    private final GigRepository gigRepository;

    //private final SessionManager sessionManager;
    private final MutableLiveData<String> venue = new MutableLiveData<>();
    private final MutableLiveData<Double> expectedPayment = new MutableLiveData<>();
    private final MutableLiveData<String> setList = new MutableLiveData<>();
    private final MutableLiveData<Date> gigDate = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isCompleted = new MutableLiveData<>(false);
    private final MutableLiveData<String> audienceFeedback = new MutableLiveData<>();
    private final MutableLiveData<Double> actualPayment = new MutableLiveData<>();

    public MutableLiveData<String> validationError = new MutableLiveData<>();

    public AddGigViewModel(GigRepository repository) {
        super();
        this.gigRepository = repository;
        //this.sessionManager = new SessionManager(gigRepository.getApplicationContext());
    }

    public void setVenue(String venue) {
        this.venue.setValue(venue);
    }

    public void setExpectedPayment(Double payment) {
        this.expectedPayment.setValue(payment);
    }

    public void setSetList(String setList) {
        this.setList.setValue(setList);
    }

    public void setGigDate(Date date) {
        this.gigDate.setValue(date);
    }

    public void setCompleted(boolean completed) {
        this.isCompleted.setValue(completed);
    }

    public void setAudienceFeedback(String feedback) {
        this.audienceFeedback.setValue(feedback);
    }

    public void setActualPayment(Double payment) {
        this.actualPayment.setValue(payment);
    }

    public LiveData<Date> getGigDate() {
        return gigDate;
    }

    public LiveData<List<Gig>> getAllGigs() {
        return gigRepository.getAllGigs();

    }

    private final MutableLiveData<Boolean> saveSuccess = new MutableLiveData<>();

    public LiveData<Boolean> getSaveSuccess() {
        return saveSuccess;
    }

    public void saveGig(int gigId, int userId,String venue, double expectedPayment, String setList,
                       String date, boolean isCompleted, String audienceFeedback, double actualPayment) {
//            userId = sessionManager.getUserId();
        if (userId == -1) {
            saveSuccess.setValue(false);
            return;
        }

        if(venue == null || venue.trim().isEmpty()){
            saveSuccess.setValue(false);
            validationError.setValue("Venue cannot be empty.");
            return;
        }

        if(date == null || date.isEmpty() || date.equals("Click here to Select a Date")){
            saveSuccess.setValue(false);
            validationError.setValue("Date cannot be empty.");
            return;
        }

        String formattedDate = gigDate.getValue() != null ?
                new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(gigDate.getValue()) : null;

        Gig gig = new Gig(userId,
                venue,
                setList != null ? setList : "",
                expectedPayment,
                actualPayment,
                date,
                audienceFeedback != null ? audienceFeedback : "",
                isCompleted
        );

        if (gigId != -1) {
            gig.setId(gigId);
            gigRepository.updateGig(gig, () -> saveSuccess.postValue(true));
        } else {
            gigRepository.insertGig(gig, () -> saveSuccess.postValue(true));
        }
    }
}
