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

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    @Column(nullable = false)
    private LocalDate donationDate;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String bloodType;

    @Column(nullable = false)
    private String location;

    public Donation(Donor donor, LocalDate donationDate, Integer quantity, String location, String bloodType) {
        this.donor = donor;
        this.donationDate = donationDate;
        this.quantity = quantity;
        this.location = location;
        this.bloodType = bloodType;
    }

    public Donation(Donor donor, LocalDate donationDate, Integer quantity, String location) {
        this.donor = donor;
        this.donationDate = donationDate;
        this.quantity = quantity;
        this.location = location;
        this.bloodType = (donor != null) ? donor.getBloodType() : null;
    }
}
