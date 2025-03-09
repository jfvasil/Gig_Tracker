package com.example.d424_captstone_jv.ui.Auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.d424_captstone_jv.R;
import com.example.d424_captstone_jv.ui.Auth.UserViewModel;
import com.example.d424_captstone_jv.ui.Auth.UserViewModelFactory;
import com.example.d424_captstone_jv.databinding.FragmentSignInBinding;
import com.example.d424_captstone_jv.ui.SessionManager;

public class SignInFragment extends Fragment {

    private FragmentSignInBinding binding;
    private UserViewModel userViewModel;

    private SessionManager sessionManager;

    public SignInFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        UserViewModelFactory factory = new UserViewModelFactory(requireActivity().getApplication());
        userViewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);

        sessionManager = new SessionManager(requireContext());

        EditText emailInput = binding.emailInput;
        EditText passwordInput = binding.passwordInput;
        Button signInButton = binding.signInButton;
        TextView signUpLink = binding.signUpLink;

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);


        signInButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Email and Password are required!", Toast.LENGTH_SHORT).show();
            } else {
                userViewModel.authenticateUser(email, password);
            }
        });

        signUpLink.setOnClickListener(v -> navController.navigate(R.id.signUpFragment));

        userViewModel.getAuthenticationSuccess().observe(getViewLifecycleOwner(), success -> {
            if (success) {
                Toast.makeText(getContext(), "Login Successful!", Toast.LENGTH_SHORT).show();

                userViewModel.getUserByEmail(emailInput.getText().toString().trim()).observe(getViewLifecycleOwner(), user -> {
                    if (user != null) {
                        sessionManager.saveUserSession(user.getId(), user.getEmail());
                        navController.navigate(R.id.nav_dashboard);
                    }

                });
            }
            else {
                Toast.makeText(getContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
            }
        });


        userViewModel.getAuthenticationMessage().observe(getViewLifecycleOwner(), message -> {
            if (message != null && !message.isEmpty()) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
