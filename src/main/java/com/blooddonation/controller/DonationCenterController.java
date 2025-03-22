package com.blooddonation.controller;

import com.blooddonation.model.DonationCenter;
import com.blooddonation.service.DonationCenterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donation-centers")
public class DonationCenterController {

    private final DonationCenterService donationCenterService;

    public DonationCenterController(DonationCenterService donationCenterService) {
        this.donationCenterService = donationCenterService;
    }

    @GetMapping
    public List<DonationCenter> getAllCenters() {
        return donationCenterService.getAllCenters();
    }

    @PostMapping
    public DonationCenter addCenter(@RequestBody DonationCenter center) {
        return donationCenterService.addCenter(center);
    }

    @DeleteMapping("/{id}")
    public void deleteCenter(@PathVariable Long id) {
        donationCenterService.deleteCenter(id);
    }
}

