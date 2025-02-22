package blooddonation;

import com.blooddonation.controller.DonationController;
import com.blooddonation.model.Donation;
import com.blooddonation.service.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
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
        Donation donation = new Donation(1L, "A+", 500, 1L);
        when(donationService.saveDonation(any(Donation.class))).thenReturn(donation);

        mockMvc.perform(post("/api/donations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"bloodType\":\"A+\",\"quantity\":500,\"donorId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.bloodType").value("A+"));
    }

    @Test
    void getDonation_ShouldReturnDonationById() throws Exception {
        Donation donation = new Donation(1L, "A+", 500, 1L);
        when(donationService.getDonation(1L)).thenReturn(donation);

        mockMvc.perform(get("/api/donations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.bloodType").value("A+"));
    }

    @Test
    void updateDonation_ShouldReturnUpdatedDonation() throws Exception {
        Donation updatedDonation = new Donation(1L, "O+", 600, 1L);
        when(donationService.updateDonation(eq(1L), any(Donation.class))).thenReturn(updatedDonation);

        mockMvc.perform(put("/api/donations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"bloodType\":\"O+\",\"quantity\":600,\"donorId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
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
        List<Donation> donations = List.of(new Donation(1L, "A+", 500, 1L));
        when(donationService.getDonationsByDonorId(1L)).thenReturn(donations);

        mockMvc.perform(get("/api/donations/donor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].bloodType").value("A+"));
    }
}
