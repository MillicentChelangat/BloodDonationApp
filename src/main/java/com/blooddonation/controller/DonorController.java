package com.blooddonation.controller;

import com.blooddonation.model.Donor;
import com.blooddonation.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    @Autowired
    private DonorService donorService;

    // Get all donors
    @GetMapping
    public ResponseEntity<List<Donor>> getAllDonors() {
        List<Donor> donors = donorService.getAllDonors();
        if (donors.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 instead of 400
        }
        return ResponseEntity.ok(donors);
    }

    // Get a donor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Donor> findDonorById(@PathVariable Long id) {
        Optional<Donor> donor = Optional.ofNullable(donorService.findDonorById(id));

        return donor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new donor
    @PostMapping
    public ResponseEntity<Donor> createDonor(@RequestBody Donor donor) {
        Donor savedDonor = donorService.saveDonor(donor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDonor);
    }

    // Update an existing donor
    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody Donor donor) {
        Donor updatedDonor = donorService.updateDonor(id, donor);
        return ResponseEntity.ok(updatedDonor);
    }

    // Delete a donor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return ResponseEntity.ok("Donor deleted successfully");
    }
}
