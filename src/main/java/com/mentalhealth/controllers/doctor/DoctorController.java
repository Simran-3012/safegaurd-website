package com.mentalhealth.controllers.doctor;


import com.mentalhealth.constants.Constants;
import com.mentalhealth.dto.doctor.DoctorCreateDTO;
import com.mentalhealth.dto.doctor.DoctorDTO;
import com.mentalhealth.dto.response.ResponseDTO;
import com.mentalhealth.enums.Role;
import com.mentalhealth.services.doctor.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
@Valid
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<?>> registerDocter(@Valid @RequestBody DoctorCreateDTO doctorCreateDTO) {
        doctorService.registerDocter(doctorCreateDTO);
        DoctorDTO doctorDTO = new DoctorDTO(
                doctorCreateDTO.getFirstName(),
                doctorCreateDTO.getLastName(),
                doctorCreateDTO.getEmail(),
                doctorCreateDTO.getLicenceType(),
                doctorCreateDTO.getLicenceNumber(),
                doctorCreateDTO.getState(),
                Role.DOCTOR);

        ResponseDTO<DoctorDTO> response = ResponseDTO.<DoctorDTO>builder()
                .statusCode(Constants.SUCCESS_CODE)
                .statusMessage(Constants.SUCCESS_MESSAGE)
                .data(doctorDTO)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/fetchAllDoctors")
    public ResponseEntity<ResponseDTO<?>> fetchAllDoctors() {
        List<DoctorDTO> doctorDTOList = doctorService.fetchAllDoctors();
        ResponseDTO<List<DoctorDTO>> response = ResponseDTO.<List<DoctorDTO>>builder()
                .statusCode(Constants.SUCCESS_CODE)
                .statusMessage(Constants.SUCCESS_MESSAGE)
                .data(doctorDTOList)
                .build();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

}
