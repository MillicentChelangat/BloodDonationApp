package com.blooddonation.service;

import com.blooddonation.model.Donation;
import com.blooddonation.repository.DonationRepository;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new NoSuchElementException(STR."Donation not found with id \{id}"));
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @SuppressWarnings("unused") // Suppress warning
    public List<Donation> getDonationsByDonorId(Long donorId) {
        return donationRepository.findByDonorId(donorId);
    }

    public Donation updateDonation(Long id, Donation donationDetails) {
        Donation donation = getDonation(id);

        if (donationDetails.getDonationDate() != null) {
            donation.setDonationDate(donationDetails.getDonationDate());
        }
        if (donationDetails.getQuantity() != null) {
            donation.setQuantity(donationDetails.getQuantity());
        }
        if (donationDetails.getLocation() != null) {
            donation.setLocation(donationDetails.getLocation());
        }
        if (donationDetails.getDonor() != null) {
            donation.setDonor(donationDetails.getDonor());
        }

        return donationRepository.save(donation);
    }

    public void deleteDonation(Long id) {
        Donation donation = getDonation(id);
        donationRepository.delete(donation);
    }

    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }
}
