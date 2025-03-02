package com.blooddonation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link donation to a donor
    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    // Store donor ID separately if needed for testing
    private Long donorId;

    // The date when the donation was made
    private LocalDate donationDate;

    // The quantity donated (e.g., in milliliters)
    private Integer quantity;

    // Blood type of the donor
    private String bloodType;

    // Donation location
    private String location;

    public Donation(Donor donor, LocalDate donationDate, Integer quantity, String location, String bloodType) {
        this.donor = donor;
        this.donorId = (donor != null) ? donor.getId() : null; // Store donor ID separately
        this.donationDate = donationDate;
        this.quantity = quantity;
        this.location = location;
        this.bloodType = bloodType;
    }

    public Donation(Donor donor, LocalDate donationDate, int quantity, String location) {
        this.donor = donor;
        this.donationDate = donationDate;
        this.quantity = quantity;
        this.location = location;
    }

}