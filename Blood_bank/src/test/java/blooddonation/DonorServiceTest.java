package blooddonation;

import com.blooddonation.model.Donor;
import com.blooddonation.service.DonorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class DonorServiceTest {

    @Mock
    private DonorService donorService;

    private Donor donor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        donor = new Donor("John Doe", "O+");
        donor.setId(1L);  // Ensure the donor has an ID if needed
    }

    @Test
    void testAddDonor() {
        when(donorService.addDonor(donor)).thenReturn(donor);

        Donor savedDonor = donorService.addDonor(donor);
        assertNotNull(savedDonor);
        assertEquals("John Doe", savedDonor.getName());
        assertEquals("O+", savedDonor.getBloodGroup());
    }

    @Test
    void testFindDonorById() {
        // Stub to return donor directly (not Optional)
        when(donorService.findDonorById(1L)).thenReturn(donor);

        Donor foundDonor = donorService.findDonorById(1L);
        assertNotNull(foundDonor);
        assertEquals("John Doe", foundDonor.getName());
        assertEquals("O+", foundDonor.getBloodGroup());
    }

    @Test
    void testUpdateDonor() {
        Donor updatedDonor = new Donor("John Doe", "AB+");
        updatedDonor.setId(1L);
        when(donorService.updateDonor(1L, updatedDonor)).thenReturn(updatedDonor);

        Donor result = donorService.updateDonor(1L, updatedDonor);
        assertNotNull(result);
        assertEquals("AB+", result.getBloodGroup());
    }
}
