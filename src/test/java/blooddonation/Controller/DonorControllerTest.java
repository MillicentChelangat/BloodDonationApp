package blooddonation.Controller;

import com.blooddonation.controller.DonorController;
import com.blooddonation.model.Donor;
import com.blooddonation.service.DonorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DonorControllerTest {

    @Mock
    private DonorService donorService; // Mock the service layer

    @InjectMocks
    private DonorController donorController; // Inject the mocked service into the controller

    private Donor testDonor;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    @BeforeEach
    public void setup() {
        // Convert String to LocalDate
        LocalDate dateOfBirth = LocalDate.parse("01/01/1990", formatter);
        LocalDate lastDonationDate = LocalDate.parse("01/01/2025", formatter);
        // Create a sample donor for testing
        testDonor = new Donor(1L, "John Doe", "Male", dateOfBirth, "A+", "0734567890",
                "johndoe@example.com", "123 Street Name", lastDonationDate);
    }

    @Test
    void testCreateDonor() {
        // Stub the service method to return the test donor when saving
        when(donorService.saveDonor(testDonor)).thenReturn(testDonor);

        // Call the createDonor endpoint
        ResponseEntity<Donor> response = donorController.createDonor(testDonor);

        // Verify that the response has a 201 CREATED status and the correct donor details
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getFullName());
    }

    @Test
    void testFindDonorById() {
        // Stub the service method to return an Optional containing testDonor for ID 1
        Donor donor = new Donor(1L, "John Doe", "A+");
        when(donorService.findDonorById(1L)).thenReturn(testDonor);

        // Call the getDonor endpoint
        ResponseEntity<Donor> response = donorController.findDonorById(1L);

        // Verify that the response has a 200 OK status and the correct donor details
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getFullName());
    }

    @Test
    void testUpdateDonor() {
        // Stub the service method to return testDonor when updating donor with ID 1
        when(donorService.updateDonor(1L, testDonor)).thenReturn(testDonor);

        // Call the updateDonor endpoint
        ResponseEntity<Donor> response = donorController.updateDonor(1L, testDonor);

        // Verify that the response has a 200 OK status and the updated donor details
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getFullName());
    }

    @Test
    void testDeleteDonor() {
        // Stub the delete method (which returns void)
        doNothing().when(donorService).deleteDonor(1L);

        // Call the deleteDonor endpoint
        ResponseEntity<String> response = donorController.deleteDonor(1L);

        // Verify that the response has a 200 OK status and the expected success message
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Donor deleted successfully", response.getBody());
    }
}

