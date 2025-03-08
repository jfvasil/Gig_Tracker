package com.example.d424_captstone_jv.ui.GigList;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d424_captstone_jv.Database.GigRepository;
import com.example.d424_captstone_jv.R;
import com.example.d424_captstone_jv.ui.GigAdapter;

import java.util.ArrayList;

public class GigListFragment extends Fragment {
    private GigListViewModel gigListViewModel;
    private EditText searchBar;
    private RadioButton radioCompleted, radioUpcoming;
    private GigAdapter gigAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gig_list, container, false);


        GigRepository gigRepository = new GigRepository(requireActivity().getApplication());


        GigListViewModelFactory factory = new GigListViewModelFactory(gigRepository);
        gigListViewModel = new ViewModelProvider(this, factory).get(GigListViewModel.class);

        RecyclerView recyclerView = root.findViewById(R.id.gig_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        gigAdapter = new GigAdapter(new ArrayList<>());
        recyclerView.setAdapter(gigAdapter);

        searchBar = root.findViewById(R.id.search_gigs);
        radioCompleted = root.findViewById(R.id.filter_completed);
        radioUpcoming = root.findViewById(R.id.filter_upcoming);



        gigListViewModel.getFilteredGigs().observe(getViewLifecycleOwner(), gigs -> {
            gigAdapter.setGigs(gigs);
        });


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                gigListViewModel.setSearchQuery(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });


        radioCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) gigListViewModel.setFilter(true);
        });

        radioUpcoming.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) gigListViewModel.setFilter(false);
        });


        gigListViewModel.getGigs().observe(getViewLifecycleOwner(), gigs -> {
            gigAdapter.setGigs(gigs);
        });

        return root;
    }
}


