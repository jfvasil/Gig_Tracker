package com.example.d424_captstone_jv.ui.AddGig;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.d424_captstone_jv.databinding.FragmentAddGigBinding;

public class AddGigFragment extends Fragment {

    private FragmentAddGigBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        AddGigViewModel addGigViewModel =
                new ViewModelProvider(this).get(AddGigViewModel.class);

        binding = FragmentAddGigBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


//        final TextView textView = binding.textAddGig;
//        addGigViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
