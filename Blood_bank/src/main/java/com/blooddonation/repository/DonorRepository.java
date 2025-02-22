package com.blooddonation.repository;

import com.blooddonation.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    // No need to implement any methods, JpaRepository handles it
}
