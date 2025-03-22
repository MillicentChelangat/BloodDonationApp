package com.blooddonation.service;

import com.blooddonation.model.DonationCenter;
import com.blooddonation.repository.DonationCenterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationCenterService {

    private final DonationCenterRepository donationCenterRepository;

    public DonationCenterService(DonationCenterRepository donationCenterRepository) {
        this.donationCenterRepository = donationCenterRepository;
    }

    public List<DonationCenter> getAllCenters() {
        return donationCenterRepository.findAll();
    }

    public DonationCenter addCenter(DonationCenter center) {
        return donationCenterRepository.save(center);
    }

    public void deleteCenter(Long id) {
        donationCenterRepository.deleteById(id);
    }
}
