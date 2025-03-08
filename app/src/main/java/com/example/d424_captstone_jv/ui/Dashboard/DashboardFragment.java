package com.example.d424_captstone_jv.ui.Dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d424_captstone_jv.Database.GigRepository;
import com.example.d424_captstone_jv.R;

import com.example.d424_captstone_jv.ui.GigAdapter;


import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    private DashboardViewModel dashboardViewModel;
    private GigAdapter gigAdapter;
    private TextView totalPaymentsTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        GigRepository gigRepository = new GigRepository(requireActivity().getApplication());

        DashboardViewModelFactory factory = new DashboardViewModelFactory(gigRepository);
        dashboardViewModel = new ViewModelProvider(this, factory).get(DashboardViewModel.class);



        RecyclerView upcomingGigsRecyclerView = root.findViewById(R.id.gig_recycler_view);
        upcomingGigsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gigAdapter = new GigAdapter(new ArrayList<>());
        upcomingGigsRecyclerView.setAdapter(gigAdapter);

        totalPaymentsTextView = root.findViewById(R.id.payment_summary_text);


        dashboardViewModel.getUpcomingGigs().observe(getViewLifecycleOwner(), gigs -> {
            gigAdapter.setGigs(gigs);
        });


        dashboardViewModel.getTotalPayments().observe(getViewLifecycleOwner(), totalPayments -> {
            String paymentText = totalPayments != null ? String.format("$%.2f", totalPayments) : "$0.00";
            totalPaymentsTextView.setText(paymentText);
        });

        return root;
    }
}
