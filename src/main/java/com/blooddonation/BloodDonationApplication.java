package com.blooddonation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.blooddonation.security", "com.blooddonation"}) // Ensure security package is scanned
public class BloodDonationApplication {
    public static void main(String[] args) {
        SpringApplication.run(BloodDonationApplication.class, args);
    }
}

