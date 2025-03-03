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
        if (userRepository.findByUsernameOrEmail (registrationDTO.getEmail()).isPresent()) {
            throw new RuntimeException("User with this name  already exists!");
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
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(request.getUsername());
        if (userOptional.isPresent() && passwordEncoder.matches(request.getPassword(), userOptional.get().getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return new AuthenticationResponseDTO(token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}

