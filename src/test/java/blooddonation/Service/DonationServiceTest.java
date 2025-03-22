package blooddonation.Service;

import com.blooddonation.model.Donation;
import com.blooddonation.model.Donor;
import com.blooddonation.repository.DonationRepository;
import com.blooddonation.service.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DonationServiceTest {

    @Mock
    private DonationRepository donationRepository;

    @InjectMocks
    private DonationService donationService;

    private Donation donation;
    private Donor donor;

    @BeforeEach
    void setUp() {
        donor = new Donor(1L, "John Doe", "A+"); // Creating a Donor object
        donation = new Donation();
        donation.setDonationId(1L);
        donation.setBloodType("A+");
        donation.setQuantity(500);
        donation.setDonor(donor);  // Assigning a Donor object
    }

    @Test
    void getDonation_ShouldReturnDonationById() {
        when(donationRepository.findById(1L)).thenReturn(Optional.of(donation));

        Donation found = donationService.getDonation(1L);

        assertEquals(1L, found.getDonationId());
        assertEquals("A+", found.getBloodType());
        assertEquals(1L, found.getDonor().getId()); // Verifying donor's ID

        verify(donationRepository, times(1)).findById(1L);
    }

    @Test
    void getDonation_ShouldThrowException_WhenNotFound() {
        when(donationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> donationService.getDonation(1L));

        verify(donationRepository, times(1)).findById(1L);
    }

    @Test
    void getAllDonations_ShouldReturnListOfDonations() {
        List<Donation> donations = Collections.singletonList(donation);
        when(donationRepository.findAll()).thenReturn(donations);

        List<Donation> result = donationService.getAllDonations();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        verify(donationRepository, times(1)).findAll();
    }

    @Test
    void saveDonation_ShouldReturnSavedDonation() {
        when(donationRepository.save(donation)).thenReturn(donation);

        Donation saved = donationService.saveDonation(donation);

        assertNotNull(saved);
        assertEquals(1L, saved.getDonationId());

        verify(donationRepository, times(1)).save(donation);
    }

    @Test
    void deleteDonation_ShouldCallRepositoryDelete() {
        when(donationRepository.findById(1L)).thenReturn(Optional.of(donation));
        doNothing().when(donationRepository).delete(donation);

        donationService.deleteDonation(1L);

        verify(donationRepository, times(1)).findById(1L);
        verify(donationRepository, times(1)).delete(donation);
    }

    @Test
    void updateDonation_ShouldReturnUpdatedDonation() {
        Donation updatedDetails = new Donation();
        updatedDetails.setDonationId(1L);
        updatedDetails.setBloodType("A+");
        updatedDetails.setQuantity(500);
        updatedDetails.setDonor(new Donor(1L, "John Doe", "A+")); // Correct Donor object

        when(donationRepository.findById(1L)).thenReturn(Optional.of(donation));
        when(donationRepository.save(any(Donation.class))).thenReturn(updatedDetails);

        Donation updated = donationService.updateDonation(1L, updatedDetails);

        assertEquals(500, updated.getQuantity());
        assertEquals("A+", updated.getBloodType());
        assertEquals(1L, updated.getDonor().getId());

        // Capture the exact Donation object passed to save()
        ArgumentCaptor<Donation> donationCaptor = ArgumentCaptor.forClass(Donation.class);
        verify(donationRepository).save(donationCaptor.capture());
        Donation savedDonation = donationCaptor.getValue();

        assertEquals("A+", savedDonation.getBloodType());
        assertEquals(500, savedDonation.getQuantity());
    }
}
