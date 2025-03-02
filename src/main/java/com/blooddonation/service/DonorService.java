package com.blooddonation.service;

import com.blooddonation.model.Donor;
import com.blooddonation.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    // Get all donors
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    // Find donor by ID
    public Optional<Donor> getDonorById(Long id) {
        return donorRepository.findById(id);
    }

    // Save a donor
    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    // Delete a donor
    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }

    public Donor addDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public Donor updateDonor(long id, Donor updatedDonor) {
        Optional<Donor> existingDonorOpt = donorRepository.findById(id);
        if (existingDonorOpt.isPresent()) {
            Donor existingDonor = existingDonorOpt.get();
            existingDonor.setFullName(updatedDonor.getFullName());
            existingDonor.setGender(updatedDonor.getGender());
            existingDonor.setDateOfBirth(updatedDonor.getDateOfBirth());
            existingDonor.setBloodType(updatedDonor.getBloodType());
            existingDonor.setContactNumber(updatedDonor.getContactNumber());
            existingDonor.setEmail(updatedDonor.getEmail());
            existingDonor.setAddress(updatedDonor.getAddress());
            existingDonor.setLastDonationDate(updatedDonor.getLastDonationDate());

            return donorRepository.save(existingDonor);
        } else {
            throw new RuntimeException(STR."Donor not found with ID: \{id}");

        }
    }

    public Donor findDonorById(Long id) {
        Optional<Donor> donor = donorRepository.findById(id);
        return donor.orElseThrow(() -> new RuntimeException(STR."Donor not found with ID: \{id}"));
    }

    public Optional<Donor> getDonor(Long id) {
        return donorRepository.findById(id);
    }

}
