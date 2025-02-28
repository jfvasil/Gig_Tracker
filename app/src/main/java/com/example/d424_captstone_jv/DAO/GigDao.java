package com.example.d424_captstone_jv.DAO;

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

    @Query("SELECT * FROM gigs ORDER BY id DESC")
    List<Gig> getAllGigs();

    @Query("SELECT * FROM gigs WHERE id = :gigId LIMIT 1")
    Gig getGigById(int gigId);

}
