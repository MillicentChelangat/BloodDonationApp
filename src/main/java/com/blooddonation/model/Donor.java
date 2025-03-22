package com.blooddonation.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "donors")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotNull
    @Column(name = "gender", nullable = false)
    private String gender;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @Column(name = "blood_type", nullable = false)
    private String bloodType;

    @NotNull
    @Column(name = "contact_number", nullable = false, unique = true)
    private String contactNumber;

    @NotNull
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "last_donation_date")
    private LocalDate lastDonationDate;

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;

    // Corrected Constructor for creating a new Donor
    public Donor(Long id, String fullName, String gender, LocalDate dateOfBirth,
                 String bloodType, String contactNumber, String email, String address,
                 LocalDate lastDonationDate) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.lastDonationDate = lastDonationDate;
    }

    // Optional: Constructor with limited fields (useful in some cases)
    public Donor(Long id, String fullName, String bloodType) {
        this.id = id;
        this.fullName = fullName;
        this.bloodType = bloodType;
    }

    // Default constructor required for JPA
    public Donor() {
    }
}
