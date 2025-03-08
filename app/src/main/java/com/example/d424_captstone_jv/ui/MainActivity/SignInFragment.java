package com.example.d424_captstone_jv.ui.MainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.d424_captstone_jv.R;
import com.example.d424_captstone_jv.ui.Dashboard.DashboardFragment;

public class SignInFragment extends Fragment {
    private AuthViewModel authViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        EditText emailInput = view.findViewById(R.id.emailInput);
        EditText passwordInput = view.findViewById(R.id.passwordInput);
        Button signInButton = view.findViewById(R.id.signInButton);
        TextView errorText = view.findViewById(R.id.errorText);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        signInButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            authViewModel.authenticateUser(email, password);
        });

        authViewModel.getAuthStatus().observe(getViewLifecycleOwner(), success -> {
            if (success) {

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_view, new DashboardFragment())
                        .addToBackStack(null)
                        .commit();
            } else {
                errorText.setText("Invalid credentials, please try again.");
            }
        });

        return view;
    }
}
