package blooddonation.Model;

import com.blooddonation.model.Donor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DonorTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    void testDonorConstructor() {
        // Convert String to LocalDate
        LocalDate dateOfBirth = LocalDate.parse("01/01/1990", formatter);
        LocalDate lastDonationDate = LocalDate.parse("01/01/2025", formatter);

        // Create a Donor object using the constructor with all fields
        Donor donor = new Donor(1L, "John Doe", "Male", dateOfBirth, "A+", "0734567890",
                "johndoe@example.com", "123 Street Name", lastDonationDate);

        // Verify that the Donor is initialized correctly
        assertNotNull(donor);
        assertEquals("John Doe", donor.getFullName());
        assertEquals("Male", donor.getGender());
        assertEquals(dateOfBirth, donor.getDateOfBirth());
        assertEquals("A+", donor.getBloodType());
        assertEquals("0734567890", donor.getContactNumber());
        assertEquals("johndoe@example.com", donor.getEmail());
        assertEquals("123 Street Name", donor.getAddress());
        assertEquals(lastDonationDate, donor.getLastDonationDate());
    }

    @Test
    void testPartialConstructor() {
        // Create a Donor object using the constructor with only fullName and bloodType
        Donor donor = new Donor(1L, "Jane Doe", "B+");

        // Verify the partial Donor object is initialized correctly
        assertNotNull(donor);
        assertEquals("Jane Doe", donor.getFullName());
        assertEquals("B+", donor.getBloodType());

        // Other fields should be null
        assertNull(donor.getGender());
        assertNull(donor.getDateOfBirth());
        assertNull(donor.getContactNumber());
        assertNull(donor.getEmail());
        assertNull(donor.getAddress());
        assertNull(donor.getLastDonationDate());
    }

    @Test
    void testSettersAndGetters() {
        LocalDate dateOfBirth = LocalDate.parse("01/01/1992", formatter);
        LocalDate lastDonationDate = LocalDate.parse("02/01/2025", formatter);

        Donor donor = new Donor(1L, "Jane Doe", "Female", dateOfBirth, "B+", "0987654321",
                "janedoe@example.com", "456 Another St", lastDonationDate);

        // Set new values for some fields
        donor.setFullName("Mary Jane");
        donor.setBloodType("O-");
        donor.setEmail("maryjane@example.com");
        donor.setContactNumber("0722334455");

        // Verify the changes using getters
        assertEquals("Mary Jane", donor.getFullName());
        assertEquals("O-", donor.getBloodType());
        assertEquals("maryjane@example.com", donor.getEmail());
        assertEquals("0722334455", donor.getContactNumber());
    }

    @Test
    void testInvalidBloodType() {
        Donor donor = new Donor(1L, "Test User", "InvalidType");

        // The model should either reject this (if validation is implemented) or store as-is
        assertEquals("InvalidType", donor.getBloodType());  // Adjust if validation logic is added
    }

    @Test
    void testEmptyAndNullValues() {
        // Test empty strings
        Donor donor = new Donor(1L, "", "");
        assertEquals("", donor.getFullName());
        assertEquals("", donor.getBloodType());

        // Test null values
        Donor nullDonor = new Donor(1L, null, null);
        assertNull(nullDonor.getFullName());
        assertNull(nullDonor.getBloodType());
    }

    @Test
    void testEmailFormat() {
        LocalDate dateOfBirth = LocalDate.parse("01/01/1995", formatter);
        LocalDate lastDonationDate = LocalDate.parse("02/01/2025", formatter);

        Donor donor = new Donor(1L, "John Doe", "Male", dateOfBirth, "A+", "0734567890",
                "invalid-email", "Test Address", lastDonationDate);

        // No validation in the model yet, so it should store as-is
        assertEquals("invalid-email", donor.getEmail());

        // If email validation is implemented, update this test to check for expected behavior
    }
}
