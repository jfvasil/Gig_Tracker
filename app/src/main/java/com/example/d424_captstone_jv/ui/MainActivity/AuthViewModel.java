package com.example.d424_captstone_jv.ui.MainActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {
    private final MutableLiveData<Boolean> authStatus = new MutableLiveData<>();

    public LiveData<Boolean> getAuthStatus() {
        return authStatus;
    }

    public void authenticateUser(String email, String password) {
        // Simulated authentication
        if (email.equals("test@example.com") && password.equals("password")) {
            authStatus.setValue(true);
        } else {
            authStatus.setValue(false);
        }
    }
}
