package com.mentalhealth.services.doctor;

import com.mentalhealth.config.SecurityConfig;
import com.mentalhealth.dto.doctor.DoctorCreateDTO;
import com.mentalhealth.dto.doctor.DoctorDTO;
import com.mentalhealth.enums.Role;
import com.mentalhealth.exceptions.DuplicateResourceException;
import com.mentalhealth.exceptions.ResourceNotFoundException;
import com.mentalhealth.models.Doctors;
import com.mentalhealth.repositories.doctor.DoctorRepository;
import com.mentalhealth.services.validations.EmailValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService implements IDoctorService {

    private final DoctorRepository doctorRepository;
    private final SecurityConfig securityConfig;
    private final EmailValidation emailValidation;

    @Override
    public void registerDocter(DoctorCreateDTO doctorCreateDTO) {
        emailValidation.checkIfEmailExists(doctorCreateDTO.getEmail());
        if (doctorRepository.findByLicenceNumber(doctorCreateDTO.getLicenceNumber()).isPresent()) {
            throw new DuplicateResourceException("Doctor with licence number " + doctorCreateDTO.getLicenceNumber() + " already exists");
        }
        Doctors doctors = Doctors.builder()
                .firstName(doctorCreateDTO.getFirstName())
                .lastName(doctorCreateDTO.getLastName())
                .email(doctorCreateDTO.getEmail())
                .password(securityConfig.passwordEncoder().encode(doctorCreateDTO.getPassword()))
                .licenceType(doctorCreateDTO.getLicenceType())
                .licenceNumber(doctorCreateDTO.getLicenceNumber())
                .state(doctorCreateDTO.getState())
                .role(Role.DOCTOR)
                .build();
        doctorRepository.save(doctors);
    }

    @Override
    public List<DoctorDTO> fetchAllDoctors() {
        List<Doctors> doctors = doctorRepository.findAll();
        if (doctors.isEmpty()) {
            throw new ResourceNotFoundException("Doctors not found");
        }
        return doctors.stream().map(doctor ->
                new DoctorDTO(
                        doctor.getFirstName(),
                        doctor.getLastName(),
                        doctor.getEmail(),
                        doctor.getLicenceType(),
                        doctor.getLicenceNumber(),
                        doctor.getState(),
                        doctor.getRole())
        ).toList();
    }


}
