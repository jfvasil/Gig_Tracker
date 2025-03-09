package com.example.d424_captstone_jv.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.d424_captstone_jv.DAO.UserDao;
import com.example.d424_captstone_jv.Entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {

    private final UserDao userDao;
    private static final int THREAD_COUNT = 4;
    private static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREAD_COUNT);

    public UserRepository(Application application) {
        GigDataBaseBuilder database = GigDataBaseBuilder.getDatabase(application);
        userDao = database.userDao();
    }


    public void registerUser(User user, OnUserRegisteredListener listener) {
        dbExecutor.execute(() -> {
            User existingUser = userDao.getUserByEmail(user.getEmail());
            if (existingUser == null) {
                userDao.insert(user);
                listener.onSuccess();
            } else {
                listener.onFailure("Email already registered.");
            }
        });
    }


    public void authenticateUser(String email, String password, OnUserAuthenticatedListener listener) {
        dbExecutor.execute(() -> {
            User user = userDao.getUserByEmail(email);
            if (user != null && user.verifyPassword(password)) {
                listener.onSuccess(user);
            } else {
                listener.onFailure("Invalid email or password.");
            }
        });
    }


    public LiveData<User> getUserByEmail(String email) {
        return userDao.getUserByEmailLive(email);
    }


    public interface OnUserRegisteredListener {
        void onSuccess();
        void onFailure(String message);
    }

    public interface OnUserAuthenticatedListener {
        void onSuccess(User user);
        void onFailure(String message);
    }
}
