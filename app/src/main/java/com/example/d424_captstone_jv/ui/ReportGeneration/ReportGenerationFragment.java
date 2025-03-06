package com.example.d424_captstone_jv.ui.ReportGeneration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.d424_captstone_jv.databinding.FragmentReportGeneratorBinding;

public class ReportGenerationFragment extends Fragment {

    private FragmentReportGeneratorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ReportGenerationViewModel reportGenerationViewModel =
                new ViewModelProvider(this).get(ReportGenerationViewModel.class);

        binding = FragmentReportGeneratorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // final TextView textView = binding.textReportGeneration;
        // reportGenerationViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
