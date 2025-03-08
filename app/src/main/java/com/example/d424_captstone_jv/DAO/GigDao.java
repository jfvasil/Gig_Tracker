package com.example.d424_captstone_jv.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.d424_captstone_jv.Entities.Gig;

import java.util.List;
@Dao
public interface GigDao {

    @Insert
    void insert(Gig gig);

    @Update
    void update(Gig gig);

    @Delete
    void delete(Gig gig);

    @Query("SELECT * FROM gigs ORDER BY date DESC")
    LiveData<List<Gig>> getAllGigs();

    @Query("SELECT * FROM gigs WHERE id = :gigId LIMIT 1")
    Gig getGigById(int gigId);

    @Query("SELECT * FROM gigs WHERE venue LIKE :searchQuery OR setList LIKE :searchQuery")
    LiveData<List<Gig>> searchGigs(String searchQuery);

    @Query("SELECT * FROM gigs WHERE isCompleted = 1")
    LiveData<List<Gig>> getCompletedGigs();

    @Query("SELECT * FROM gigs WHERE isCompleted = 0")
    LiveData<List<Gig>> getUpcomingGigs();

    @Query("SELECT SUM(actualPayment) FROM gigs WHERE date >= :startDate AND date <= :endDate")
    LiveData<Double> getTotalPaymentsForMonth(String startDate, String endDate);

    @Query("SELECT * FROM gigs WHERE date BETWEEN :startDate AND :endDate")
    LiveData<List<Gig>> getGigsInDateRange(String startDate, String endDate);
}


