package com.mentalhealth.services.validations;

import com.mentalhealth.exceptions.DuplicateResourceException;
import com.mentalhealth.repositories.doctor.DoctorRepository;
import com.mentalhealth.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailValidation {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    public void checkIfEmailExists(String email) {
        List<String> errors = new ArrayList<>();

        if (userRepository.findByEmail(email).isPresent() || doctorRepository.findByEmail(email).isPresent()) {
            errors.add("Email " + email + " already exists.");
        }

        if (!errors.isEmpty()) {
            throw new DuplicateResourceException(String.join(", ", errors));
        }

    }

}
