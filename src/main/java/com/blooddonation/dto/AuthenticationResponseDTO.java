package com.blooddonation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer"; // Default value

    public AuthenticationResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
