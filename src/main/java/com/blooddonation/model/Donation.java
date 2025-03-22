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
    @Column(name = "donation_id", nullable = false, unique = true)
    private Long donationId;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    @Column(name = "donation_date", nullable = false)
    private LocalDate donationDate;

    @Column(nullable = false, length = 10)
    private String bloodType;

    @Column(name = "donation_impact", columnDefinition = "INT DEFAULT 1")
    private Integer donationImpact;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer quantity;

    // Constructor ensuring correct parameter order and consistency
    public Donation(Donor donor, LocalDate donationDate, Integer quantity, String bloodType, String location) {
        this.donor = donor;
        this.donationDate = donationDate;
        this.quantity = quantity;
        this.bloodType = bloodType;
        this.location = location;
        this.donationImpact = 1; // Default impact set in Java as well
    }
}
