package blooddonation.Service;

import com.blooddonation.model.Donor;
import com.blooddonation.repository.DonorRepository;
import com.blooddonation.service.DonorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Enable Mockito's JUnit 5 support
class DonorServiceTest {

    @Mock
    private DonorRepository donorRepository;

    @InjectMocks
    private DonorService donorService;

    private Donor donor;

    @BeforeEach
    void setUp() {
        donor = new Donor(1L, "John Doe", "A+");
        donor.setId(1L);
    }

    @Test
    void testAddDonor() {
        when(donorRepository.save(any(Donor.class))).thenReturn(donor);

        Donor savedDonor = donorService.addDonor(donor);

        assertNotNull(savedDonor);
        assertEquals("John Doe", savedDonor.getFullName());
        assertEquals("A+", savedDonor.getBloodType());
        verify(donorRepository, times(1)).save(donor);
    }

    @Test
    void testFindDonorById() {
        when(donorRepository.findById(1L)).thenReturn(Optional.of(donor));

        Donor result = donorService.findDonorById(1L);

        assertNotNull(result);
        assertEquals(donor, result);
        verify(donorRepository, times(1)).findById(1L);
    }

    @Test
    void testFindDonorById_NotFound() {
        when(donorRepository.findById(1L)).thenReturn(Optional.empty()); // Simulate not found

        Exception exception = assertThrows(RuntimeException.class, () -> donorService.findDonorById(1L));

        assertEquals("Donor not found with id: 1", exception.getMessage());
        verify(donorRepository, times(1)).findById(1L);
    }


    @Test
    void testUpdateDonor() {
        when(donorRepository.findById(1L)).thenReturn(Optional.of(donor)); // Simulate existing donor

        Donor updatedDonor = new Donor(1L, "John Doe Updated", "O+");
        when(donorRepository.save(any(Donor.class))).thenReturn(updatedDonor); // Ensure save() is mocked

        Donor result = donorService.updateDonor(1L, updatedDonor);

        assertNotNull(result);
        assertEquals("O+", result.getBloodType());
        verify(donorRepository, times(1)).save(any(Donor.class)); // Ensure save() is actually called
    }

}
