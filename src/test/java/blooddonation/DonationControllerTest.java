package blooddonation;

import com.blooddonation.controller.DonationController;
import com.blooddonation.model.Donation;
import com.blooddonation.model.Donor;
import com.blooddonation.service.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DonationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DonationService donationService;

    @InjectMocks
    private DonationController donationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(donationController).build();
    }

    @Test
    void createDonation_ShouldReturnCreatedDonation() throws Exception {
        Donor donor = new Donor();
        donor.setId(1L);

        Donation donation = new Donation(donor, LocalDate.now(), 500, "Hospital A", "A+");
        when(donationService.saveDonation(any(Donation.class))).thenReturn(donation);

        mockMvc.perform(post("/api/donations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "donorId": 1,
                                    "donationDate": "2024-02-28",
                                    "quantity": 500,
                                    "location": "Hospital A",
                                    "bloodType": "A+"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.donor.id").value(1))
                .andExpect(jsonPath("$.bloodType").value("A+"));
    }

    @Test
    void getDonation_ShouldReturnDonationById() throws Exception {
        Donor donor = new Donor();
        donor.setId(1L);

        Donation donation = new Donation(donor, LocalDate.now(), 500, "Hospital A", "A+");
        when(donationService.getDonation(1L)).thenReturn(donation);

        mockMvc.perform(get("/api/donations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.donor.id").value(1))
                .andExpect(jsonPath("$.bloodType").value("A+"));
    }

    @Test
    void updateDonation_ShouldReturnUpdatedDonation() throws Exception {
        Donor donor = new Donor();
        donor.setId(1L);

        Donation updatedDonation = new Donation(donor, LocalDate.now(), 600, "Hospital B", "O+");
        when(donationService.updateDonation(eq(1L), any(Donation.class))).thenReturn(updatedDonation);

        mockMvc.perform(put("/api/donations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "donorId": 1,
                                    "donationDate": "2024-02-28",
                                    "quantity": 600,
                                    "location": "Hospital B",
                                    "bloodType": "O+"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.donor.id").value(1))
                .andExpect(jsonPath("$.bloodType").value("O+"));
    }

    @Test
    void deleteDonation_ShouldReturnNoContent() throws Exception {
        doNothing().when(donationService).deleteDonation(1L);

        mockMvc.perform(delete("/api/donations/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getDonationsByDonor_ShouldReturnDonationsForSpecificDonor() throws Exception {
        Donor donor = new Donor();
        donor.setId(1L);

        List<Donation> donations = List.of(new Donation(donor, LocalDate.now(), 500, "Hospital A", "A+"));
        when(donationService.getDonationsByDonorId(1L)).thenReturn(donations);

        mockMvc.perform(get("/api/donations/donor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].donor.id").value(1))
                .andExpect(jsonPath("$[0].bloodType").value("A+"));
    }
}
