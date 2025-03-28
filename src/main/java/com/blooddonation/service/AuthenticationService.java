package com.blooddonation.service;

import com.blooddonation.dto.AuthenticationRequestDTO;
import com.blooddonation.dto.AuthenticationResponseDTO;
import com.blooddonation.dto.UserRegistrationDTO;
import com.blooddonation.model.User;
import com.blooddonation.repository.UserRepository;
import com.blooddonation.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(UserRegistrationDTO registrationDTO) {
        // Check if the user already exists
        Optional<User> existingUser = userRepository.findByUsernameOrEmail(registrationDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with this email already exists!");
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(registrationDTO.getFullName());
        newUser.setEmail(registrationDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword())); // Encrypt password

        // Save user to database
        userRepository.save(newUser);

        return "User registered successfully!";
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail());


        if (userOptional.isEmpty()) {
            System.out.println("User not found: " + request.getUsernameOrEmail());
            throw new RuntimeException("Invalid credentials");
        }

        User user = userOptional.get();
        System.out.println("Stored password: " + user.getPassword());
        System.out.println("Entered password: " + request.getPassword());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            System.out.println("Password mismatch!");
            throw new RuntimeException("Invalid credentials");
        }


        String token = jwtUtil.generateToken(userOptional.get().getUsername());
        return new AuthenticationResponseDTO(token);
    }
}
