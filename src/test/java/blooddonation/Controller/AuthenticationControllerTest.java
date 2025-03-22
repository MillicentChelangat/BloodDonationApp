package blooddonation.Controller;

import com.blooddonation.controller.AuthenticationController;
import com.blooddonation.dto.AuthenticationRequestDTO;
import com.blooddonation.dto.AuthenticationResponseDTO;
import com.blooddonation.dto.UserRegistrationDTO;
import com.blooddonation.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)  // JUnit 5 + Mockito integration
public class AuthenticationControllerTest {

    private MockMvc mockMvc;  // Manually setting up MockMvc

    private final ObjectMapper objectMapper = new ObjectMapper();  // JSON converter

    @Mock
    private AuthenticationService authenticationService;  // Mocking the service layer

    @InjectMocks
    private AuthenticationController authenticationController;  // Injecting Mocks into the Controller

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build(); // Set up MockMvc manually
    }

    @Test
    public void testRegisterUser() throws Exception {
        UserRegistrationDTO user = new UserRegistrationDTO();
        user.setUsername("testUser");
        user.setPassword("testPass");
        user.setEmail("test@example.com");

        Mockito.when(authenticationService.registerUser(any(UserRegistrationDTO.class)))
                .thenReturn("User registered successfully");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    public void testLoginUser() throws Exception {
        AuthenticationRequestDTO loginDTO = new AuthenticationRequestDTO();
        loginDTO.setUsernameOrEmail("testUserOrEmail");
        loginDTO.setPassword("testPass");

        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO("sample-jwt-token");

        Mockito.when(authenticationService.authenticate(any(AuthenticationRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }


}
