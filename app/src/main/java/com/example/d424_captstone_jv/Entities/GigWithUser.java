package com.example.d424_captstone_jv.Entities;

import androidx.room.Embedded;

public class GigWithUser {
    @Embedded
    public Gig gig;

    public String userName;
    public String userEmail;
}