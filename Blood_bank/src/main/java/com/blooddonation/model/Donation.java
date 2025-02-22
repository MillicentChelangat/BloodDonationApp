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

    // Link donation to a donor (assuming you already have a Donor entity)
    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    // The date when the donation was made
    private LocalDate donationDate;

    // The quantity donated (for example, in milliliters)
    private Integer quantity;

    // Donation location
    private String location;
    @Setter
    private long donorId;
    @Getter
    @Setter
    private String bloodType;

    public Donation(Donor donor, LocalDate donationDate, Integer quantity, String location) {
        this.donor = donor;
        this.donationDate = donationDate;
        this.quantity = quantity;
        this.location = location;
    }
    public Donation(long l, String bloodType, int quantity, long l1) {
        this.id = l;
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.donorId = l1;
    }

}
