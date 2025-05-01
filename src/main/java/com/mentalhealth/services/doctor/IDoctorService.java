package com.mentalhealth.services.doctor;

import com.mentalhealth.dto.doctor.DoctorCreateDTO;
import com.mentalhealth.dto.doctor.DoctorDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface IDoctorService {

    void registerDocter(@Valid DoctorCreateDTO doctorCreateDTO);

    List<DoctorDTO> fetchAllDoctors();
}
