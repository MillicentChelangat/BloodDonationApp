package blooddonation;

import com.blooddonation.model.Donation;
import com.blooddonation.model.Donor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DonationTest {

    private Donation donation;

    @BeforeEach
    void setUp() {
        // Sample data
        Donor donor = new Donor();
        LocalDate donationDate = LocalDate.of(2024, 2, 21);
        int quantity = 500;
        String location = "Blood Center A";

        // Initialize donation object
        donation = new Donation(donor, donationDate, quantity, location);
    }

    @Test
    void testDonationConstructor() {
        assertNotNull(donation);
        assertNotNull(donation.getDonor());
        assertEquals(500, donation.getQuantity());
        assertEquals("Blood Center A", donation.getLocation());
    }

    @Test
    void testSetAndGetBloodType() {
        donation.setBloodType("O+");
        assertEquals("O+", donation.getBloodType());
    }

    @Test
    void testSetAndGetDonorId() {
        donation.setDonorId(1L);
        assertEquals(1L, donation.getDonorId());
    }

    @Test
    void testDonationCreation() {
        Donor donor = new Donor();
        donor.setId(1L);

        // Corrected constructor
        Donation donation = new Donation(donor, LocalDate.now(), 500, "Hospital A", "A+");

        assertNotNull(donation);
        assertEquals(1L, donation.getDonor().getId());
        assertEquals("A+", donation.getBloodType());
    }

}
