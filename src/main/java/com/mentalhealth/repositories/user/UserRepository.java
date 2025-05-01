package com.mentalhealth.repositories.user;

import com.mentalhealth.models.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email address") String email);
}
