

package com.example.d424_captstone_jv.ui.AddGig;

import android.app.Application;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.d424_captstone_jv.R;
import com.example.d424_captstone_jv.databinding.FragmentAddGigBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddGigFragment extends Fragment {

    private FragmentAddGigBinding binding;
    private AddGigViewModel addGigViewModel;
    private final Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Application application = requireActivity().getApplication();
        AddGigViewModelFactory factory = new AddGigViewModelFactory(application);
        addGigViewModel = new ViewModelProvider(this, factory).get(AddGigViewModel.class);

        binding = FragmentAddGigBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);

        EditText venueEditText = binding.inputVenue;
        EditText expectedPaymentEditText = binding.inputExpectedPayment;
        EditText setListEditText = binding.inputSetList;
        TextView dateTextView = binding.gigSelectDate;
        RadioGroup gigStatusGroup = binding.gigStatusGroup;
        RadioButton radioUpcoming = binding.radioUpcoming;
        RadioButton radioCompleted = binding.radioCompleted;
        View completedFields = binding.completedFields;
        EditText audienceFeedbackEditText = binding.inputAudienceFeedback;
        EditText actualPaymentEditText = binding.inputActualPayment;
        Button saveGigButton = binding.buttonSaveGig;

        dateTextView.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(),
                    (DatePicker view1, int year, int month, int dayOfMonth) -> {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        Date selectedDate = calendar.getTime();
                        dateTextView.setText(dateFormat.format(selectedDate));
                        addGigViewModel.setGigDate(selectedDate);
                    },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });

        gigStatusGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == radioCompleted.getId()) {
                completedFields.setVisibility(View.VISIBLE);
            } else {
                completedFields.setVisibility(View.GONE);
            }
        });



        saveGigButton.setOnClickListener(view -> {

            double expectedPayment = 0.0;
            double actualPayment = 0.0;

            if (!expectedPaymentEditText.getText().toString().isEmpty()) {
                expectedPayment = Double.parseDouble(expectedPaymentEditText.getText().toString().trim());
            }

            if (!actualPaymentEditText.getText().toString().isEmpty()) {
                actualPayment = Double.parseDouble(actualPaymentEditText.getText().toString().trim());
            }
            String venue = venueEditText.getText().toString();
            String setList = setListEditText.getText().toString();
            String gigDate = dateTextView.getText().toString();
            boolean isCompleted = radioCompleted.isChecked();
            String audienceFeedback = audienceFeedbackEditText.getText().toString();


            addGigViewModel.saveGig(venue, expectedPayment, setList, gigDate, isCompleted, audienceFeedback, actualPayment);
        });

        addGigViewModel.getSaveSuccess().observe(getViewLifecycleOwner(), success -> {
            if (success) {
                navController.navigate(R.id.nav_dashboard);
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
