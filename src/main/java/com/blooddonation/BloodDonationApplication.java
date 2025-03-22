package com.blooddonation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.blood donation"}) // ✅ Fixed package name

public class BloodDonationApplication {
    public static void main(String[] args) {
        SpringApplication.run(BloodDonationApplication.class, args);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123"; // Example password
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println(STR."Hashed Password: \{hashedPassword}");
    }
}





