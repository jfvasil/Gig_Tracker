package com.example.d424_captstone_jv.Database;

import android.app.Application;

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


    public void insertGig(Gig gig) {
        dbExecutor.execute(() -> gigDao.insert(gig));
    }


    public void updateGig(Gig gig) {
        dbExecutor.execute(() -> gigDao.update(gig));
    }

    public void deleteGig(Gig gig) {
        dbExecutor.execute(() -> gigDao.delete(gig));
    }


    public List<Gig> getAllGigs() {
        return gigDao.getAllGigs();
    }


}
