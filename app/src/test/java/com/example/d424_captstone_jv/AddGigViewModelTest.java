package com.example.d424_captstone_jv;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.d424_captstone_jv.Database.GigRepository;
import com.example.d424_captstone_jv.Entities.Gig;
import com.example.d424_captstone_jv.ui.AddGig.AddGigViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class AddGigViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private GigRepository mockGigRepository;
    private AddGigViewModel viewModel;
    private Observer<Boolean> observer;

    private final MutableLiveData<Boolean> saveSuccess = new MutableLiveData<>();

    @Before
    public void setUp() {
        mockGigRepository = mock(GigRepository.class);
        viewModel = new AddGigViewModel(mockGigRepository);
        observer = mock(Observer.class);
        viewModel.getSaveSuccess().observeForever(observer);

    }

    @Test
    public void testSaveGig_NewGig_InsertsGig() {
        viewModel.saveGig(-1, 1, "Test", 100.0, "Songs", "2025-03-10", false, "Good", 35.0);
        verify(mockGigRepository, times(1)).insertGig(any(Gig.class), any());
    }

    @Test
    public void testSaveGig_UpdateGig_UpdatesExistingGig() {
        viewModel.saveGig(5, 1, "Updated Test", 200.0, "Udated Songs", "2025-03-15", true, "Bad", 1850.0);
        verify(mockGigRepository, times(1)).updateGig(any(Gig.class),any());
    }

    @Test
    public void testRetrieveGigs_ReturnsGigs() throws InterruptedException {

        List<Gig> mockGigs = new ArrayList<>();
        mockGigs.add(new Gig(1, "Venue A", "Good Songs A", 100.0, 80.0, "2025-03-10", "Awesome", true));
        mockGigs.add(new Gig(2, "Venue B", "Good Songs B", 200.0, 158.0, "2025-03-15", "Perfect", false));

        MutableLiveData<List<Gig>> liveDataGigs = new MutableLiveData<>();
        liveDataGigs.setValue(mockGigs);


        when(mockGigRepository.getAllGigs()).thenReturn(liveDataGigs);


        List<Gig> retrievedGigs = LiveDataTestUtil.getOrAwaitValue(viewModel.getAllGigs());

        assertEquals(2, retrievedGigs.size());
        assertEquals("Venue A", retrievedGigs.get(0).getVenue());
        assertEquals("Venue B", retrievedGigs.get(1).getVenue());
    }
}
