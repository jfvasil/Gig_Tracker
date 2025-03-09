package com.example.d424_captstone_jv.ui.ReportGeneration;

import android.Manifest;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.d424_captstone_jv.Database.GigRepository;

import com.example.d424_captstone_jv.databinding.FragmentReportGeneratorBinding;
import com.example.d424_captstone_jv.ui.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReportGenerationFragment extends Fragment {
    private FragmentReportGeneratorBinding binding;
    private ReportGenerationViewModel reportViewModel;
    private final Calendar calendar = Calendar.getInstance();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Application application = requireActivity().getApplication();
        GigRepository gigRepository = new GigRepository(application);

        SessionManager sessionManager = new SessionManager(requireContext());
        int userId = sessionManager.getUserId();

        ReportGenerationViewModelFactory factory = new ReportGenerationViewModelFactory(gigRepository,userId);
        reportViewModel = new ViewModelProvider(this, factory).get(ReportGenerationViewModel.class);

        binding = FragmentReportGeneratorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.startDateInput.setOnClickListener(v -> showDatePickerDialog(true));
        binding.endDateInput.setOnClickListener(v -> showDatePickerDialog(false));


        reportViewModel.getStartDate().observe(getViewLifecycleOwner(), date -> {
            if (date != null) {
                binding.startDateInput.setText(dateFormat.format(date));
            }
        });

        reportViewModel.getEndDate().observe(getViewLifecycleOwner(), date -> {
            if (date != null) {
                binding.endDateInput.setText(dateFormat.format(date));
            }
        });


        binding.generateReportButton.setOnClickListener(v -> {
            String startDate = binding.startDateInput.getText().toString().trim();
            String endDate = binding.endDateInput.getText().toString().trim();

            if (!startDate.isEmpty() && !endDate.isEmpty()) {
                reportViewModel.getGigsInDateRange(startDate, endDate,userId).observe(getViewLifecycleOwner(), gigs -> {
                    if (gigs != null && !gigs.isEmpty()) {
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                            if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            }
                        }

                        ReportGenerator.generateGigReport(requireContext(), gigs, startDate, endDate);
                    }
                });
            }
        });

        return root;
    }

    private void showDatePickerDialog(boolean isStartDate) {
        Calendar selectedDate = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                (view, year, month, dayOfMonth) -> {
                    selectedDate.set(year, month, dayOfMonth);
                    Date date = selectedDate.getTime();

                    if (isStartDate) {
                        reportViewModel.setStartDate(date);
                    } else {
                        reportViewModel.setEndDate(date);
                    }
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}