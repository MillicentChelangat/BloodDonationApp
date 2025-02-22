package com.blooddonation.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Setter
@Getter
@Entity
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String bloodType;
    private String contactNumber;
    private String email;
    private String address;
    private String lastDonationDate;

    // Constructor for Donor with all fields (except id since it is auto-generated)
    public Donor(String fullName, String gender, String dateOfBirth, String bloodType,
                 String contactNumber, String email, String address, String lastDonationDate) {
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.lastDonationDate = lastDonationDate;
    }

    // Constructor without the id for creating a new donor
    public Donor(String fullName, String bloodType) {
        this.fullName = fullName;
        this.bloodType = bloodType;
    }

    // Default constructor (required for JPA)
    public Donor(Long id, String fullName, String bloodType) {
        this.id = id;
        this.fullName = fullName;
        this.bloodType = bloodType;
    }

    public Donor() {

    }

    public String getName() {
        return this.fullName;
    }

    public String getBloodGroup() {
        return this.bloodType;
    }

    public Object orElseThrow() {
        // Check for critical fields (you can add more checks based on your business logic)
        if (this.fullName == null || this.fullName.isEmpty()) {
            throw new RuntimeException("Donor fullName is required");
        }
        if (this.bloodType == null || this.bloodType.isEmpty()) {
            throw new RuntimeException("Donor bloodType is required");
        }
        if (this.id == null) {
            throw new RuntimeException("Donor ID is required");
        }

        // If everything is valid, return the current object
        return this;
    }
}
