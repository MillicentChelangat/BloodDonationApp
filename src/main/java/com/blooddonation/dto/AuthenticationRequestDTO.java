package com.blooddonation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor  // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
public class AuthenticationRequestDTO {
    private String usernameOrEmail;
    private String password;
}
