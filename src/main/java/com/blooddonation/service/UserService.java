package com.blooddonation.service;

import com.blooddonation.dto.AuthenticationRequestDTO;
import com.blooddonation.dto.AuthenticationResponseDTO;
import com.blooddonation.dto.UserRegistrationDTO;
import com.blooddonation.enums.Role;
import com.blooddonation.model.User;
import com.blooddonation.repository.UserRepository;
import com.blooddonation.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;  // FIX: Autowire JwtUtil instead of declaring it in the method

    public String register(UserRegistrationDTO request) {
        if (userRepository.findByUsernameOrEmail(request.getUsernameOrEmail()).isPresent()) {
            throw new IllegalArgumentException("Email or Username already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? Role.valueOf(request.getRole()) : Role.DONOR);

        userRepository.save(user);
        return "User registered successfully!";
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO request) {
        Optional<User> userOpt = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail());

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // FIX: Generate token after password validation
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthenticationResponseDTO(token);
    }
}
