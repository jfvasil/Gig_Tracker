package com.example.d424_captstone_jv.Database;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.d424_captstone_jv.DAO.GigDao;
import com.example.d424_captstone_jv.DAO.UserDao;
import com.example.d424_captstone_jv.Entities.Gig;
import com.example.d424_captstone_jv.Entities.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class GigRepository {

    private final UserDao userDao;
    private final GigDao gigDao;
    private static final int THREAD_COUNT = 4;
    private static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREAD_COUNT);

    public GigRepository(Application application) {
        GigDataBaseBuilder db = GigDataBaseBuilder.getDatabase(application);
        userDao = db.userDao();
        gigDao = db.gigDao();
    }


    public void insertUser(User user) {
        dbExecutor.execute(() -> userDao.insert(user));
    }


    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }


    public void insertGig(Gig gig, Runnable onSuccess) {
        dbExecutor.execute(() -> {
            gigDao.insert(gig);
            onSuccess.run();
        });
    }


    public void updateGig(Gig gig, Runnable onSuccess) {
        dbExecutor.execute(() -> gigDao.update(gig));
        onSuccess.run();
    }

    public void deleteGig(Gig gig) {
        dbExecutor.execute(() -> gigDao.delete(gig));
    }

    public LiveData<List<Gig>> searchGigs(String query, int userId) {
        return gigDao.searchGigs("%" + query + "%", userId);
    }

    public LiveData<List<Gig>> getCompletedGigs(int userId) {
        return gigDao.getCompletedGigs(userId);
    }

    public LiveData<List<Gig>> getUpcomingGigs(int userId) {
        return gigDao.getUpcomingGigs(userId);
    }

    public LiveData<Double> getTotalPaymentsForMonth(String startDate, String endDate, int userId) {
        return gigDao.getTotalPaymentsForMonth(startDate, endDate, userId);
    }

    public LiveData<List<Gig>> getAllGigs() {
        return gigDao.getAllGigs();
    }

    public LiveData<List<Gig>> getGigsInDateRange(String startDate, String endDate, int userId) {
        return gigDao.getGigsInDateRange(startDate, endDate, userId);
    }

    public LiveData<List<Gig>> getGigsForUser(int userId) {
        return gigDao.getGigsForUser(userId);
    }



}
