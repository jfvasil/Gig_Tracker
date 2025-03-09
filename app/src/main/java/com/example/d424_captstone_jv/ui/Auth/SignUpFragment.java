package com.example.d424_captstone_jv.ui.Auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.d424_captstone_jv.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;
    private UserViewModel userViewModel;

    public SignUpFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        UserViewModelFactory factory = new UserViewModelFactory(requireActivity().getApplication());
        userViewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);


        EditText nameInput = binding.nameInput;
        EditText emailInput = binding.emailInput;
        EditText passwordInput = binding.passwordInput;
        Button signUpButton = binding.signUpButton;

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);


        signUpButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "All fields are required!", Toast.LENGTH_SHORT).show();
            } else {
                userViewModel.registerUser(name, email, password);
            }
        });


        userViewModel.getRegistrationSuccess().observe(getViewLifecycleOwner(), success -> {
            if (success) {
                Toast.makeText(getContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.signInFragment);
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
