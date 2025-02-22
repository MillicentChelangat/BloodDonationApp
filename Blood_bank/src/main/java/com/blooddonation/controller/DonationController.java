package com.blooddonation.controller;

import com.blooddonation.model.Donation;
import com.blooddonation.service.DonationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    // Create a new donation
    @PostMapping
    public ResponseEntity<Donation> createDonation(@RequestBody Donation donation) {
        Donation savedDonation = donationService.saveDonation(donation);
        return new ResponseEntity<>(savedDonation, HttpStatus.CREATED);
    }

    // Retrieve all donations
    @GetMapping
    public ResponseEntity<List<Donation>> getAllDonations() {
        List<Donation> donations = donationService.getAllDonations();
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    // Retrieve a specific donation by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Donation> getDonation(@PathVariable Long id) {
        Donation donation = donationService.getDonation(id);
        return new ResponseEntity<>(donation, HttpStatus.OK);
    }

    // Update an existing donation by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Donation> updateDonation(@PathVariable Long id, @RequestBody Donation donationDetails) {
        Donation updatedDonation = donationService.updateDonation(id, donationDetails);
        return new ResponseEntity<>(updatedDonation, HttpStatus.OK);
    }

    // Delete a donation by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Retrieve all donations for a specific donor
    @GetMapping("/donor/{donorId}")
    public ResponseEntity<List<Donation>> getDonationsByDonor(@PathVariable Long donorId) {
        List<Donation> donations = donationService.getDonationsByDonorId(donorId);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }
}
