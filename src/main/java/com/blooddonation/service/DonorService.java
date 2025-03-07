package com.blooddonation.service;

import com.blooddonation.model.Donor;
import com.blooddonation.repository.DonorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DonorService {

    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    // Get all donors
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }
    // Find donor by ID
    public Donor findDonorById(Long id) {
        return donorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donor not found with id: " + id));
    }

    // Save a donor
    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    // Delete a donor
    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }

    // Add a new donor
    public Donor addDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    // Update donor details
    public Donor updateDonor(long id, Donor updatedDonor) {
        return donorRepository.findById(id)
                .map(existingDonor -> {
                    existingDonor.setFullName(updatedDonor.getFullName());
                    existingDonor.setGender(updatedDonor.getGender());
                    existingDonor.setDateOfBirth(updatedDonor.getDateOfBirth());
                    existingDonor.setBloodType(updatedDonor.getBloodType());
                    existingDonor.setContactNumber(updatedDonor.getContactNumber());
                    existingDonor.setEmail(updatedDonor.getEmail());
                    existingDonor.setAddress(updatedDonor.getAddress());
                    existingDonor.setLastDonationDate(updatedDonor.getLastDonationDate());

                    return donorRepository.save(existingDonor);
                })
                .orElseThrow(() -> new NoSuchElementException("Donor not found with id: " + id));
    }

}
