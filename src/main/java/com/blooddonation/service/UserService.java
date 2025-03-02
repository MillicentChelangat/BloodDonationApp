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
    private JwtUtil jwtUtil;

    public String register(UserRegistrationDTO request) {
        if (userRepository.findByUsernameOrEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : Role.DONOR);

        userRepository.save(user);
        return "User registered successfully!";
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO request) {
        Optional<User> userOpt = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail());

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
            String token = jwtUtil.generateToken(user.getUsername());
            return new AuthenticationResponseDTO(token);
        }
    }
}


