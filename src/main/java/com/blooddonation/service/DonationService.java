package com.blooddonation.service;

import com.blooddonation.model.Donation;
import com.blooddonation.repository.DonationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Donation getDonation(Long id) {
        return donationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Donation not found with id: " + id));
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    public void deleteDonation(Long id) {
        Donation donation = getDonation(id); // Ensure donation exists before deleting
        donationRepository.delete(donation);
    }

    public Donation updateDonation(Long id, Donation updatedDetails) {
        Donation existingDonation = getDonation(id); // Fetch the existing donation

        // Update details
        existingDonation.setBloodType(updatedDetails.getBloodType());
        existingDonation.setQuantity(updatedDetails.getQuantity());
        existingDonation.setDonor(updatedDetails.getDonor());

        return donationRepository.save(existingDonation); // Save and return the updated object
    }
    public List<Donation> searchDonations(String donorName, LocalDate donationDate, String bloodType) {
        return donationRepository.searchDonations(donorName, donationDate, bloodType);
    }
    public List<Donation> getDonationsByDonorId(Long donorId) {
        return donationRepository.findByDonorId(donorId);
    }

}
