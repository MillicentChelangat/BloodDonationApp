package com.blooddonation.controller;

import com.blooddonation.dto.AuthenticationRequestDTO;
import com.blooddonation.dto.AuthenticationResponseDTO;
import com.blooddonation.dto.UserRegistrationDTO;
import com.blooddonation.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // User Registration
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
        String response = authenticationService.registerUser(registrationDTO);
        return ResponseEntity.ok(response);
    }

    // User Login
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> loginUser(@RequestBody AuthenticationRequestDTO loginDTO) {
        AuthenticationResponseDTO response = authenticationService.authenticate(loginDTO);
        return ResponseEntity.ok(response);
    }
}

