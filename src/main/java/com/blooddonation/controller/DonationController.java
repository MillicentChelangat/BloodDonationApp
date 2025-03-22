package com.blooddonation.controller;

import com.blooddonation.model.Donation;
import com.blooddonation.service.DonationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


@RestController
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }
        // Create donation
        @PostMapping
        public ResponseEntity<Donation> createDonation(@RequestBody Donation donation) {
            Donation savedDonation = donationService.saveDonation(donation);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDonation);
        }

        // Get donation by ID
        @GetMapping("/{id}")
        public ResponseEntity<Donation> getDonation(@PathVariable Long id) {
            Donation donation = donationService.getDonation(id);
            if (donation != null) {
                return ResponseEntity.ok(donation);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Update donation
        @PutMapping("/{id}")
        public ResponseEntity<Donation> updateDonation(@PathVariable Long id, @RequestBody Donation updatedDonation) {
            Donation donation = donationService.updateDonation(id, updatedDonation);
            return ResponseEntity.ok(donation);
        }

        // Delete donation
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
            donationService.deleteDonation(id);
            return ResponseEntity.noContent().build();
        }

    @GetMapping("/search")
    public List<Donation> searchDonations(
            @RequestParam(required = false) String donorName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate donationDate,
            @RequestParam(required = false) String bloodType) {

        return donationService.searchDonations(donorName, donationDate, bloodType);
    }

        // Get donations by donor
        @GetMapping("/donor/{donorId}")
        public ResponseEntity<List<Donation>> getDonationsByDonor(@PathVariable Long donorId) {
            List<Donation> donations = donationService.getDonationsByDonorId(donorId);
            return ResponseEntity.ok(donations);
        }
    }

