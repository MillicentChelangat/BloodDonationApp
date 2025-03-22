package com.blooddonation.repository;

import com.blooddonation.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.time.LocalDate;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByDonorId(Long donorId);

    // Combined Search with Query
    @Query("SELECT d FROM Donation d WHERE " +
            "(:donorName IS NULL OR d.donor.fullName LIKE %:donorName%) AND " +
            "(:donationDate IS NULL OR d.donationDate = :donationDate) AND " +
            "(:bloodType IS NULL OR d.bloodType = :bloodType)")
    List<Donation> searchDonations(
            @Param("donorName") String donorName,
            @Param("donationDate") LocalDate donationDate,
            @Param("bloodType") String bloodType
    );

}

