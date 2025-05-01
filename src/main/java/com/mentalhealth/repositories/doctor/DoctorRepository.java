package com.mentalhealth.repositories.doctor;


import com.mentalhealth.models.Doctors;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctors, Long> {

    Optional<Doctors> findByEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email address") String email);

    Optional<Object> findByLicenceNumber(@NotBlank(message = "Licence number is required") @Size(min = 3, max = 50, message = "Licence number must be between 3 and 50 characters") String licenceNumber);
}
