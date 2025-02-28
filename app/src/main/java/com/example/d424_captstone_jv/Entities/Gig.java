package com.example.d424_captstone_jv.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gigs")
public class Gig {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String venue;
    public String setList;
    public double expectedPayment;
    public double actualPayment;
    public String audienceFeedback;
    public boolean isCompleted;
    public boolean isPaid;

    public Gig(String venue, String setList, double expectedPayment, double actualPayment,
               String audienceFeedback, boolean isCompleted, boolean isPaid) {
        this.venue = venue;
        this.setList = setList;
        this.expectedPayment = expectedPayment;
        this.actualPayment = actualPayment;
        this.audienceFeedback = audienceFeedback;
        this.isCompleted = isCompleted;
        this.isPaid = isPaid;
    }

    public Gig() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getSetList() {
        return setList;
    }

    public void setSetList(String setList) {
        this.setList = setList;
    }

    public double getExpectedPayment() {
        return expectedPayment;
    }

    public void setExpectedPayment(double expectedPayment) {
        this.expectedPayment = expectedPayment;
    }

    public double getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(double actualPayment) {
        this.actualPayment = actualPayment;
    }

    public String getAudienceFeedback() {
        return audienceFeedback;
    }

    public void setAudienceFeedback(String audienceFeedback) {
        this.audienceFeedback = audienceFeedback;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
