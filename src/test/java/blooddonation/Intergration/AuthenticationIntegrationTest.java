package blooddonation.Intergration; // ✅ Fix package name

import com.blooddonation.BloodDonationApplication;
import com.blooddonation.dto.AuthenticationRequestDTO;
import com.blooddonation.dto.UserRegistrationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = BloodDonationApplication.class) // ✅ Ensure app loads
@AutoConfigureMockMvc
@Transactional // ✅ Reset DB after each test
public class AuthenticationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // ✅ Use Spring's autowired instance

    @BeforeEach
    void setup() {
        // ✅ No need to manually set up MockMvc, @AutoConfigureMockMvc does it
    }

    @Test
    void testUserRegistrationAndLogin() throws Exception {
        // ✅ Step 1: Register a new user
        UserRegistrationDTO newUser = new UserRegistrationDTO();
        newUser.setUsername("testUser");
        newUser.setPassword("TestPass123");
        newUser.setEmail("testuser@example.com");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated());

        // ✅ Step 2: Login with the registered user
        AuthenticationRequestDTO loginRequest = new AuthenticationRequestDTO();
        loginRequest.setUsernameOrEmail("testUser");
        loginRequest.setPassword("TestPass123");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists()); // ✅ Ensure JWT token is returned
    }
}
