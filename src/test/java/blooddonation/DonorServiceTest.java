package blooddonation;

import com.blooddonation.model.Donor;
import com.blooddonation.repository.DonorRepository;
import com.blooddonation.service.DonorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


class DonorServiceTest {

    @Mock
    private DonorRepository donorRepository; // Fix repository mock

    @InjectMocks
    private DonorService donorService; // Inject DonorService

    private Donor donor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        donor = new Donor("John Doe", "O+");
        donor.setId(1L);
    }

    @Test
    void testAddDonor() {
        when(donorRepository.save(donor)).thenReturn(donor);

        Donor savedDonor = donorService.addDonor(donor);
        assertNotNull(savedDonor);
        assertEquals("John Doe", savedDonor.getName());
        assertEquals("O+", savedDonor.getBloodGroup());
    }

    @Test
    void testFindDonorById() {
        when(donorRepository.findById(1L)).thenReturn(Optional.of(donor));

        Donor result = donorService.findDonorById(1L);
        assertNotNull(result);
        assertEquals(donor, result);
    }

    @Test
    void testUpdateDonor() {
        when(donorRepository.findById(1L)).thenReturn(Optional.of(donor));
        Donor updatedDonor = new Donor("John Doe", "AB+");
        updatedDonor.setId(1L);
        when(donorRepository.save(any())).thenReturn(updatedDonor);


        Donor result = donorService.updateDonor(1L, updatedDonor);

        assertNotNull(result);
        assertEquals("AB+", result.getBloodGroup());
    }

}
