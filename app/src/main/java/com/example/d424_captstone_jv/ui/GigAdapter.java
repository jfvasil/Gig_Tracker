package com.example.d424_captstone_jv.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d424_captstone_jv.Entities.Gig;
import com.example.d424_captstone_jv.R;
import com.example.d424_captstone_jv.ui.GigList.GigListViewModel;

import java.util.List;

public class GigAdapter extends RecyclerView.Adapter<GigAdapter.GigViewHolder> {

    private List<Gig> gigList;

    private final GigListViewModel gigListViewModel;

    private final Context context;

    public GigAdapter(Context context, GigListViewModel gigListViewModel, List<Gig> gigList) {
        this.context = context;
        this.gigList = gigList;
        this.gigListViewModel = gigListViewModel;
    }


    @NonNull
    @Override
    public GigViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gig, parent, false);
        return new GigViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GigViewHolder holder, int position) {
        Gig gig = gigList.get(position);
        holder.venueTextView.setText("Venue: " + gig.getVenue());
        holder.dateTextView.setText("Date: " + gig.getDate());
        holder.expectedPaymentTextView.setText("Expected Payment: $" + gig.getExpectedPayment());
        holder.actualPaymentTextView.setText("Actual Payment: $" + gig.getActualPayment());
        holder.setListTextView.setText("Set List: " + gig.getSetList());
        holder.audienceFeedbackTextView.setText("Audience Feedback: " + gig.getAudienceFeedback());
        holder.isCompletedTextView.setText("Completed: " + (gig.isCompleted() ? "Yes" : "No"));

        if (gigListViewModel != null) {
            holder.deleteButton.setVisibility(View.VISIBLE);
            holder.deleteButton.setOnClickListener(v -> {
                gigListViewModel.deleteGig(gig);
            });
        } else {
            holder.deleteButton.setVisibility(View.GONE);
        }

        holder.editButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController((View) v.getParent());
            Bundle bundle = new Bundle();
            bundle.putInt("gigId", gig.getId());
            bundle.putString("venue", gig.getVenue());
            bundle.putDouble("expectedPayment", gig.getExpectedPayment());
            bundle.putString("setList", gig.getSetList());
            bundle.putString("date", gig.getDate());
            bundle.putBoolean("isCompleted", gig.isCompleted());
            bundle.putString("audienceFeedback", gig.getAudienceFeedback());
            bundle.putDouble("actualPayment", gig.getActualPayment());

            navController.navigate(R.id.nav_add_gig, bundle);
        });

    }
    @Override
    public int getItemCount() {
        return gigList.size();
    }

    public void setGigs(List<Gig> newGigs) {
        gigList.clear();
        gigList.addAll(newGigs);
        notifyDataSetChanged();
    }


    static class GigViewHolder extends RecyclerView.ViewHolder {
        TextView venueTextView, dateTextView, expectedPaymentTextView, actualPaymentTextView;
        TextView setListTextView, audienceFeedbackTextView, isCompletedTextView;

        ImageButton deleteButton, editButton;

        GigViewHolder(View itemView) {
            super(itemView);
            venueTextView = itemView.findViewById(R.id.venueTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            expectedPaymentTextView = itemView.findViewById(R.id.expectedPaymentTextView);
            actualPaymentTextView = itemView.findViewById(R.id.actualPaymentTextView);
            setListTextView = itemView.findViewById(R.id.setListTextView);
            audienceFeedbackTextView = itemView.findViewById(R.id.audienceFeedbackTextView);
            isCompletedTextView = itemView.findViewById(R.id.isCompletedTextView);
            deleteButton = itemView.findViewById(R.id.button_delete_gig);
            editButton = itemView.findViewById(R.id.button_edit_gig);
        }
    }
}
