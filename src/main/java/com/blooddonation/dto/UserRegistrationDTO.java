package com.blooddonation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String role; // "DONOR" or "ADMIN"
}

