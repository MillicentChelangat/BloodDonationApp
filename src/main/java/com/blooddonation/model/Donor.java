package com.blooddonation.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "donors") // Optional: Ensures table name is explicitly set
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "donor_id", nullable = false)
    private String donorID;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "blood_type", nullable = false)
    private String bloodType;

    @Column(name = "contact_number", nullable = false, unique = true)
    private String contactNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "last_donation_date")
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
        if (this.fullName == null || this.fullName.isEmpty()) {
            throw new RuntimeException("Donor fullName is required");
        }
        if (this.bloodType == null || this.bloodType.isEmpty()) {
            throw new RuntimeException("Donor bloodType is required");
        }
        if (this.id == null) {
            throw new RuntimeException("Donor ID is required");
        }
        return this;
    }
}
