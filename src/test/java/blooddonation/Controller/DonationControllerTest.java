package blooddonation.Controller;

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
import java.util.Collections;
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

    private Donation donation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(donationController).build();

        Donor donor = new Donor();
        donor.setId(1L);
        donation = new Donation(donor, LocalDate.now(), 500, "Nairobi Hospital", "A+");
    }

    @Test
    void createDonation_ShouldReturnCreatedDonation() throws Exception {
        when(donationService.saveDonation(any(Donation.class))).thenReturn(donation);

        mockMvc.perform(post("/api/donations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                {
                                    "donorId": 1,
                                    "donationDate": "%s",
                                    "quantity": 500,
                                    "location": "Nairobi Hospital",
                                    "bloodType": "A+"
                                }
                                """, donation.getDonationDate())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.donor.id").value(1))
                .andExpect(jsonPath("$.bloodType").value("A+"));

        verify(donationService, times(1)).saveDonation(any(Donation.class));
    }

    @Test
    void getDonation_ShouldReturnDonationById() throws Exception {
        when(donationService.getDonation(1L)).thenReturn(donation);

        mockMvc.perform(get("/api/donations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.donor.id").value(1))
                .andExpect(jsonPath("$.bloodType").value("A+"));

        verify(donationService, times(1)).getDonation(1L);
    }

    @Test
    void updateDonation_ShouldReturnUpdatedDonation() throws Exception {
        Donation updatedDonation = new Donation(donation.getDonor(), LocalDate.now(), 600, "Kenyatta Hospital", "A+");

        when(donationService.updateDonation(eq(1L), any(Donation.class))).thenReturn(updatedDonation);

        mockMvc.perform(put("/api/donations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                {
                                    "donorId": 1,
                                    "donationDate": "%s",
                                    "quantity": 600,
                                    "location": "Kenyatta Hospital",
                                    "bloodType": "A+"
                                }
                                """, updatedDonation.getDonationDate())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.donor.id").value(1))
                .andExpect(jsonPath("$.bloodType").value("A+"));

        verify(donationService, times(1)).updateDonation(eq(1L), any(Donation.class));
    }

    @Test
    void deleteDonation_ShouldReturnNoContent() throws Exception {
        doNothing().when(donationService).deleteDonation(1L);

        mockMvc.perform(delete("/api/donations/1"))
                .andExpect(status().isNoContent());

        verify(donationService, times(1)).deleteDonation(1L);
    }

    @Test
    void getDonationsByDonor_ShouldReturnDonationsForSpecificDonor() throws Exception {
        List<Donation> donations = Collections.singletonList(donation);
        when(donationService.getDonationsByDonorId(1L)).thenReturn(donations);

        mockMvc.perform(get("/api/donations/donor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].donor.id").value(1))
                .andExpect(jsonPath("$[0].bloodType").value("A+"));

        verify(donationService, times(1)).getDonationsByDonorId(1L);
    }

    @Test
    void getDonation_ShouldReturnNotFound_WhenDonationDoesNotExist() throws Exception {
        when(donationService.getDonation(99L)).thenReturn(null);

        mockMvc.perform(get("/api/donations/99"))
                .andExpect(status().isNotFound());

        verify(donationService, times(1)).getDonation(99L);
    }

    @Test
    void testSearchDonations() throws Exception {
        mockMvc.perform(get("/api/donations/search")
                        .param("donorName", "John Doe")
                        .param("donationDate", "2024-03-12")
                        .param("bloodType", "A+"))
                .andExpect(status().isOk());
    }
}
