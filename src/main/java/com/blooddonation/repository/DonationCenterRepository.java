package com.blooddonation.repository;

import com.blooddonation.model.DonationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationCenterRepository extends JpaRepository<DonationCenter, Long> {
}

