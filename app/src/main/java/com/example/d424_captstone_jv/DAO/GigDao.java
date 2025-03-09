package com.example.d424_captstone_jv.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.d424_captstone_jv.Entities.Gig;
import com.example.d424_captstone_jv.Entities.GigWithUser;

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

    @Query("SELECT * FROM gigs WHERE venue LIKE :searchQuery OR setList LIKE :searchQuery AND userId = :userId")
    LiveData<List<Gig>> searchGigs(String searchQuery, int userId);

    @Query("SELECT * FROM gigs WHERE isCompleted = 1 AND userId = :userId")
    LiveData<List<Gig>> getCompletedGigs(int userId);

    @Query("SELECT * FROM gigs WHERE isCompleted = 0 AND userId = :userId ")
    LiveData<List<Gig>> getUpcomingGigs(int userId);

    @Query("SELECT SUM(actualPayment) FROM gigs WHERE date >= :startDate AND date <= :endDate AND userId = :userId AND isCompleted = 1")
    LiveData<Double> getTotalPaymentsForMonth(String startDate, String endDate, int userId);

    @Query("SELECT * FROM gigs WHERE date BETWEEN :startDate AND :endDate AND userId = :userId" )
    LiveData<List<Gig>> getGigsInDateRange(String startDate, String endDate, int userId);

    @Query("SELECT * FROM gigs WHERE userId = :userId ORDER BY date DESC")
    LiveData<List<Gig>> getGigsForUser(int userId);

    @Query("SELECT gigs.*, users.name AS userName, users.email AS userEmail " +
            "FROM gigs INNER JOIN users ON gigs.userId = users.id " +
            "WHERE gigs.userId = :userId")
    LiveData<List<GigWithUser>> getGigsWithUserDetails(int userId);

}


