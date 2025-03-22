package com.blooddonation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor  // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
public class UserRegistrationDTO {
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String role; // "DONOR" or "ADMIN"

    public String getUsernameOrEmail() {
        return (username != null && !username.isEmpty()) ? username : email;
    }
}
