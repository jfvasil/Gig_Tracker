package com.example.d424_captstone_jv.ui.Auth;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.d424_captstone_jv.Database.UserRepository;
import com.example.d424_captstone_jv.Entities.User;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private final MutableLiveData<String> authenticationMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> authenticationSuccess = new MutableLiveData<>();
    private final MutableLiveData<Boolean> registrationSuccess = new MutableLiveData<>();

    public UserViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }


    public void registerUser(String name, String email, String password) {
        User newUser = new User(name, email, password);
        userRepository.registerUser(newUser, new UserRepository.OnUserRegisteredListener() {
            @Override
            public void onSuccess() {
                registrationSuccess.postValue(true);
            }

            @Override
            public void onFailure(String message) {
                authenticationMessage.postValue(message);
            }
        });
    }


    public void authenticateUser(String email, String password) {
        userRepository.authenticateUser(email, password, new UserRepository.OnUserAuthenticatedListener() {
            @Override
            public void onSuccess(User user) {
                authenticationSuccess.postValue(true);
            }

            @Override
            public void onFailure(String message) {
                authenticationMessage.postValue(message);
            }
        });
    }


    public LiveData<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }


    public LiveData<String> getAuthenticationMessage() {
        return authenticationMessage;
    }

    public LiveData<Boolean> getAuthenticationSuccess() {
        return authenticationSuccess;
    }

    public LiveData<Boolean> getRegistrationSuccess() {
        return registrationSuccess;
    }
}
