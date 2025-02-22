package blooddonation;

import com.blooddonation.model.Donor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DonorTest {

    @Test
    void testDonorConstructor() {
        // Create a Donor object using the constructor with all fields
        Donor donor = new Donor("John Doe", "Male", "01/01/1990", "O+", "1234567890", "johndoe@example.com",
                "123 Street Name", "2025-01-01");

        // Verify that the Donor is initialized correctly
        assertNotNull(donor);
        assertEquals("John Doe", donor.getFullName());
        assertEquals("Male", donor.getGender());
        assertEquals("01/01/1990", donor.getDateOfBirth());
        assertEquals("O+", donor.getBloodType());
        assertEquals("1234567890", donor.getContactNumber());
        assertEquals("johndoe@example.com", donor.getEmail());
        assertEquals("123 Street Name", donor.getAddress());
        assertEquals("2025-01-01", donor.getLastDonationDate());
    }

    @Test
    void testPartialConstructor() {
        // Create a Donor object using the constructor with only fullName and bloodType
        Donor donor = new Donor("Jane Doe", "A+");

        // Verify the partial Donor object is initialized correctly
        assertNotNull(donor);
        assertEquals("Jane Doe", donor.getFullName());
        assertEquals("A+", donor.getBloodType());
    }

    @Test
    void testSettersAndGetters() {
        Donor donor = new Donor("Jane Doe", "Female", "01/01/1992", "B+", "0987654321", "janedoe@example.com",
                "456 Another St", "2025-01-02");

        // Set new values for some fields
        donor.setFullName("Mary Jane");
        donor.setBloodType("O-");

        // Verify the changes using getters
        assertEquals("Mary Jane", donor.getFullName());
        assertEquals("O-", donor.getBloodType());
    }
}
