package com.example.d424_captstone_jv.ui.GigList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.d424_captstone_jv.databinding.FragmentGigListBinding;

public class GigListFragment extends Fragment {

    private FragmentGigListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        GigListViewModel gigListViewModel =
                new ViewModelProvider(this).get(GigListViewModel.class);

        binding = FragmentGigListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // final TextView textView = binding.textGigList;
        // gigListViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
