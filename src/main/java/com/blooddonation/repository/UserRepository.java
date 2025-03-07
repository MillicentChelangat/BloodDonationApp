package com.blooddonation.repository;

import com.blooddonation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :value OR u.email = :value")
    Optional<User> findByUsernameOrEmail(@Param("value") String value);



}
