package com.example.d424_captstone_jv.ui.AddGig;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d424_captstone_jv.Database.GigRepository;
import com.example.d424_captstone_jv.Entities.Gig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddGigViewModel extends ViewModel {
    private final GigRepository gigRepository;
    private final MutableLiveData<String> venue = new MutableLiveData<>();
    private final MutableLiveData<Double> expectedPayment = new MutableLiveData<>();
    private final MutableLiveData<String> setList = new MutableLiveData<>();
    private final MutableLiveData<Date> gigDate = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isCompleted = new MutableLiveData<>(false);
    private final MutableLiveData<String> audienceFeedback = new MutableLiveData<>();
    private final MutableLiveData<Double> actualPayment = new MutableLiveData<>();

    public AddGigViewModel(GigRepository repository) {
        this.gigRepository = repository;
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

    private final MutableLiveData<Boolean> saveSuccess = new MutableLiveData<>();

    public LiveData<Boolean> getSaveSuccess() {
        return saveSuccess;
    }

    public void saveGig(String venue, double expectedPayment, String setList,
                       String date, boolean isCompleted, String audienceFeedback, double actualPayment) {
        String formattedDate = gigDate.getValue() != null ?
                new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(gigDate.getValue()) : null;

                Gig gig = new Gig(
                venue != null ? venue : "",
                setList != null ? setList : "",
                expectedPayment,
                actualPayment,
                date != null ? date : "",
                audienceFeedback != null ? audienceFeedback : "",
                isCompleted
        );
        gigRepository.insertGig(gig, () -> saveSuccess.postValue(true));
    }
}
