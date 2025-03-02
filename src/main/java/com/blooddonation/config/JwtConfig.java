package com.blooddonation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    // Secret key for signing JWT tokens (Should be securely stored, not hardcoded in production)
    public static final String SECRET_KEY = "mysecretkey";  // Use an environment variable or secure vault in production

    // Define a bean to provide the secret key for signing and validating JWT tokens
    @Bean
    public String jwtSecretKey() {
        return SECRET_KEY;
    }
}

