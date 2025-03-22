package blooddonation.Model;

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
        String bloodType = "A+"; // Required parameter
        String location = "Blood Center A"; // Required parameter

        // Initialize donation object with correct constructor
        donation = new Donation(donor, donationDate, quantity, bloodType, location);
    }

    @Test
    void testDonationConstructor() {
        assertNotNull(donation);
        assertNotNull(donation.getDonor());
        assertEquals(500, donation.getQuantity());
        assertEquals("Blood Center A", donation.getLocation());
        assertEquals("A+", donation.getBloodType());
    }

    @Test
    void testSetAndGetBloodType() {
        donation.setBloodType("B-");
        assertEquals("B-", donation.getBloodType());
    }

    @Test
    void testSetAndGetDonor() {
        Donor donor = new Donor(1L, "John Doe", "O+"); // Create a valid Donor object
        donation.setDonor(donor); // Set the donor
        assertEquals(1L, donation.getDonor().getId()); // Get donor's ID
    }

    @Test
    void testDonationCreation() {
        Donor donor = new Donor();
        donor.setId(2L);

        // Create a new Donation object with the correct constructor
        Donation newDonation = new Donation(donor, LocalDate.now(), 600, "AB-", "Nairobi Hospital");

        assertNotNull(newDonation);
        assertEquals(2L, newDonation.getDonor().getId());
        assertEquals("AB-", newDonation.getBloodType());
        assertEquals("Nairobi Hospital", newDonation.getLocation());
    }
}
