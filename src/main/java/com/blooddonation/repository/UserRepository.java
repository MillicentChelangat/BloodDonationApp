package com.blooddonation.repository;

import com.blooddonation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username);

    Optional<Object> findByUsername(String email);
}
